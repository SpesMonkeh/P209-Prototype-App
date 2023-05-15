package com.p209.dinero.core.common.navigation

const val HOME_TOP_ROUTE: String = "HomeScreen"
const val PANTRY_TOP_ROUTE: String = "PantryScreen"
const val BUDGET_TOP_ROUTE: String = "BudgetScreen"
const val APP_SETTINGS_TOP_ROUTE: String = "AppSettingsScreen"
const val USER_PREFERENCES_TOP_ROUTE: String = "UserPreferencesScreen"
const val ONBOARDING_TOP_ROUTE: String = "OnboardingScreen"

sealed class TopScreen(val route: String) {
	object Home: TopScreen(route = HOME_TOP_ROUTE)

	object Pantry: TopScreen(route = PANTRY_TOP_ROUTE)

	object Budget: TopScreen(route = BUDGET_TOP_ROUTE)

	object Onboarding: TopScreen(route = ONBOARDING_TOP_ROUTE)

	object AppSettings: TopScreen(route = APP_SETTINGS_TOP_ROUTE)

	object UserPreferences: TopScreen(route = USER_PREFERENCES_TOP_ROUTE)

	// TODO INDSÆT => object Profile: Screen(route = PROFILE_TOP_ROUTE) Måske.

	// TODO INDSÆT => object Shop: Screen(route = SHOPPING_TOP_ROUTE)
}