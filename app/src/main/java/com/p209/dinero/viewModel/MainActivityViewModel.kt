package com.p209.dinero.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.core.common.navigation.TopScreen
import com.p209.dinero.core.data.repository.AppDataRepository
import com.p209.dinero.core.data.repository.UserDataRepository
import com.p209.dinero.core.model.data.UserData
import com.p209.dinero.viewModel.MainActivityUiState.Loading
import com.p209.dinero.viewModel.MainActivityUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
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
	private val timeOut: Long = 5000L

	private val isLoading: MutableState<Boolean> = mutableStateOf(true)
	private val startDestination: MutableState<String> = mutableStateOf(TopScreen.Onboarding.route)
	private val onboardingCompleted: MutableState<Boolean> = mutableStateOf(false)

	val IsLoading: State<Boolean> = isLoading
	val StartDestination: State<String> = startDestination
	val OnboardingCompleted: State<Boolean> = onboardingCompleted

	val uiState: StateFlow<MainActivityUiState> =
		userDataRepository.userData.map { Success(it) }
			.stateIn(
				scope = viewModelScope,
				initialValue = Loading,
				started = SharingStarted.WhileSubscribed(timeOut)
			)

	init {
		viewModelScope.launch {
			userDataRepository.userData.collect { userData ->
				startDestination.value = when (userData.onboardingCompleted) {
					true -> TopScreen.Home.route
					false -> TopScreen.Onboarding.route
				}
				onboardingCompleted.value = userData.onboardingCompleted
			}
		}
	}
}

sealed interface MainActivityUiState {
	object Loading: MainActivityUiState
	data class Success(val userData: UserData): MainActivityUiState
}