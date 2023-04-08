package com.p209.dinero.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.core.data.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
	private val userDataRepo: UserDataRepository,
) : ViewModel() {
	private val timeOut: Long = 5_000
	private val showOnboarding: Flow<Boolean> = userDataRepo.userData.map { it.showOnboarding }

	val onboardingUiState: StateFlow<OnboardingUiState> =
		combine(showOnboarding, userDataRepo.getUserName()) {
			showOnboarding, userName ->
			if(showOnboarding) {
				OnboardingUiState.Shown(userName)
			} else {
				OnboardingUiState.Hidden
			}
		}
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(timeOut),
				initialValue = OnboardingUiState.Loading
			)

	fun dismissOnboarding() {
		viewModelScope.launch {
			userDataRepo.setShowOnboarding(false)
		}
	}
}

private fun UserDataRepository.getUserName(): Flow<String> = userData.map { userData ->
	userData.userName ?: "[UNNAMED USER]" // TODO Midlertidig løsning, og strengen skal måske flyttes til relevant XML-resource fil.
}