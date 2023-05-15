package com.p209.dinero.onboarding

/**
 * *Sealed interface*
 *
 * Describes the user's onboarding-process, if there is not already a user connected to the app.
 */
sealed interface OnboardingUiState {

	/** Onboarding State is being loaded. */
	object Loading: OnboardingUiState

	/** Onboarding State could not be loaded. */
	object LoadFailed: OnboardingUiState

	/**
	 * Onboarding State was not found.
	 *
	 * (called `NotShown` in *Now in Android*)
	 */
	object Hidden: OnboardingUiState

	/** Onboarding State was found. */
	data class Shown(
		val userName: String
	): OnboardingUiState {

		/**
		 * `true`; if the user has provided a [userName]
		 *
		 * [TODO] gør afhængig af andre, obligatoriske opsætningsvariable
		 */
		val canBeDismissed: Boolean get() = userName.isNotBlank() && userName.isNotEmpty()
	}

	/** Onboarding of the user is still ongoing. */
	object Ongoing: OnboardingUiState

	/**  Onboarding of the user has been completed. */
	object Completed: OnboardingUiState
}