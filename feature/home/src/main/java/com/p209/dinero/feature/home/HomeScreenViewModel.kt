package com.p209.dinero.feature.home
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
	val userDataRepo: UserDataRepository,
) : ViewModel() {
	private val timeOut: Long = 5_000
	private val showOnboarding: Flow<Boolean> = userDataRepo.userData.map { !it.hideOnboarding }

	val onboardingUiState: StateFlow<OnboardingUiState> =
		combine(showOnboarding, userDataRepo.getUserNameAsFlow()) {
			showOnboarding, userName ->
			if(showOnboarding) {
				OnboardingUiState.Shown(userName)
			} else {
				OnboardingUiState.Hidden
			}
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(timeOut),
				initialValue = OnboardingUiState.Loading
			)

	fun dismissOnboarding() {
		viewModelScope.launch {
			userDataRepo.setHideOnboarding(true)
		}
	}

	fun setUsername(updatedName: String) {
		viewModelScope.launch {
			userDataRepo.setUsername(updatedName)
		}
	}

	fun VerifyUsername(username: String): Boolean = username.isNotBlank() && username.isNotEmpty()
}

private fun UserDataRepository.getUserNameAsFlow(): Flow<String> = userData.map { userData ->
	userData.username ?: "[UNNAMED USER]" // TODO Midlertidig løsning, og strengen skal måske flyttes til relevant XML-resource fil.
}