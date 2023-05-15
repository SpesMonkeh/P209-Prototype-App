package com.p209.dinero.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.ViewModel
import com.p209.dinero.core.data.repository.AppDataRepository
import com.p209.dinero.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalFoundationApi
@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
	val userDataRepository: UserDataRepository,
	val appDataRepository: AppDataRepository,
): ViewModel() {
	private val timeOut: Long = 5000L

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
}

private fun UserDataRepository.getUserNameAsFlow(): Flow<String> =
	userData.map { userData ->
		userData.username ?: "[UNNAMED USER]" // TODO Midlertidig løsning, og strengen skal måske flyttes til relevant XML-resource fil.
	}