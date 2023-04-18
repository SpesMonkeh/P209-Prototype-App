package com.p209.dinero.feature.onboarding

/**
 * *Sealed interface*
 *
 * Beskriver brugerens onboarding-tilstand, hvis der ingen bruger er forbundet til app'en.
 *
 * `
 */
sealed interface OnboardingUiState {

	/**
	 * Onboarding State hentes.
	 *
	 * `
	 */
	object Loading: OnboardingUiState

	/**
	 * Onboarding State kunne ikke hentes.
	 *
	 * `
	 */
	object LoadFailed: OnboardingUiState

	/**
	 * Onboarding State findes ikke.
	 *
	 * (objektet kaldes `NotShown` i *Now in Android*)
	 *
	 * `
	 */
	object Hidden: OnboardingUiState

	/**
	 * Onboarding State findes.
	 *
	 * `
	 */
	data class Shown(
		val userName: String
	): OnboardingUiState {

		/**
		 * `true`; hvis brugeren har opgivet et [userName]
		 *
		 * //[TODO] gør afhængig af andre, obligatoriske opsætningsvariable
		 *
		 * `
		 */
		val canBeDismissed: Boolean get() = userName.isNotBlank() && userName.isNotEmpty()
	}
}