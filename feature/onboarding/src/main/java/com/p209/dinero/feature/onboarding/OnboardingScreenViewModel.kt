package com.p209.dinero.feature.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.core.data.repository.AppDataRepository
import com.p209.dinero.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalFoundationApi
@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
	val userDataRepository: UserDataRepository,
	val appDataRepository: AppDataRepository,
): ViewModel() {
	private val timeOut: Long = 5000L
	private val onboardingCompleted: Flow<Boolean> = userDataRepository.userData.map {
		!it.onboardingCompleted
	}

	val onboardingUiState: StateFlow<OnboardingUiState> = onboardingCompleted.map {
			isOngoing ->
		if (isOngoing) {
			OnboardingUiState.Ongoing
		} else {
			OnboardingUiState.Completed
		}
	}.stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(timeOut),
		initialValue = OnboardingUiState.Loading
	)


	//val onboardingUiState: StateFlow<OnboardingUiState> =
	//	combine(onboardingOngoing, userDataRepository.getUserNameAsFlow()) {
	//		showOnboarding, username ->
	//		if(showOnboarding) {
	//			OnboardingUiState.Shown(username)
	//		} else {
	//			OnboardingUiState.Hidden
	//		}
	//	}.stateIn(
	//		scope = viewModelScope,
	//		started = SharingStarted.WhileSubscribed(timeOut),
	//		initialValue = OnboardingUiState.Loading
	//	)

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

private fun UserDataRepository.getUserNameAsFlow(): Flow<String> =
	userData.map { userData ->
		userData.username ?: "[UNNAMED USER]" // TODO Midlertidig løsning, og strengen skal måske flyttes til relevant XML-resource fil.
	}