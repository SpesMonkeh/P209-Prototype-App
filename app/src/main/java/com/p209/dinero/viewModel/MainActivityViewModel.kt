package com.p209.dinero.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.core.common.navigation.DineoNavGraph
import com.p209.dinero.core.data.repository.AppDataRepository
import com.p209.dinero.core.data.repository.UserDataRepository
import com.p209.dinero.core.model.data.UserData
import com.p209.dinero.onboarding.OnboardingUiState
import com.p209.dinero.viewModel.MainActivityUiState.Loading
import com.p209.dinero.viewModel.MainActivityUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
	private val userDataRepository: UserDataRepository,
	val appDataRepository: AppDataRepository,
) : ViewModel() {

	private val userData = userDataRepository.userData
	private val timeOut: Long = 5000L
	private val startDestination: MutableState<String> = mutableStateOf(DineoNavGraph.Onboarding.route)
	private val onboardingCompleted: MutableState<Boolean> = mutableStateOf(false)

	val StartDestination: State<String> = startDestination
	val OnboardingCompleted: State<Boolean> = onboardingCompleted

	val uiState: StateFlow<MainActivityUiState> =
		userDataRepository.userData.map { Success(it) }
			.stateIn(
				scope = viewModelScope,
				initialValue = Loading,
				started = SharingStarted.WhileSubscribed(timeOut)
			)

	val onboardingUiState: StateFlow<OnboardingUiState> = userData.map {
		if (it.onboardingCompleted) {
			OnboardingUiState.Ongoing
		} else {
			OnboardingUiState.Completed
		}
	}.stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(timeOut),
		initialValue = OnboardingUiState.Loading
	)

	init {
		viewModelScope.launch {
			userDataRepository.userData.collect { userData ->
				startDestination.value = when (userData.onboardingCompleted) {
					true -> DineoNavGraph.Home.route
					false -> DineoNavGraph.Onboarding.route
				}
				onboardingCompleted.value = userData.onboardingCompleted
				Log.d("MainActivityVM init", "OnboardingCompleted => ${onboardingCompleted.value} ")
				Log.d("MainActivityVM init", "Start Destination => ${startDestination.value}")
			}
		}
	}

	fun saveOnboardingState(completed: Boolean) {
		viewModelScope.launch(Dispatchers.IO) {
			userDataRepository.saveOnboardingState(completed = completed)
		}
	}

	fun dismissOnboarding() {
		viewModelScope.launch {
			userDataRepository.saveOnboardingState(true)
		}
	}

	fun setUsername(username: String) {
		viewModelScope.launch {
			userDataRepository.setUsername(username)
		}
	}

	fun verifyUsername(username: String?): Boolean = !username.isNullOrEmpty()
}

sealed interface MainActivityUiState {
	object Loading: MainActivityUiState
	data class Success(val userData: UserData): MainActivityUiState
}