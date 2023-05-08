package com.p209.dinero.feature.onboarding.navigation

const val ONBOARDING_WELCOME_ROUTE: String = "Onboarding_Welcome"
const val ONBOARDING_LANGUAGE_ROUTE: String = "Onboarding_Language"
const val ONBOARDING_USERNAME_ROUTE: String = "Onboarding_Username"

const val ONBOARDING_FINISH_ROUTE: String = "Onboarding_Finish"

sealed class Screen(val route: String) {
    object Welcome: Screen(route = ONBOARDING_WELCOME_ROUTE)
    object SetLanguage: Screen(route = ONBOARDING_LANGUAGE_ROUTE)
    object SetUsername: Screen(route = ONBOARDING_USERNAME_ROUTE)

    object Finish: Screen(route = ONBOARDING_FINISH_ROUTE)
}