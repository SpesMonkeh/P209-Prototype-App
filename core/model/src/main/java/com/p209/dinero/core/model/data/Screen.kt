package com.p209.dinero.core.model.data

const val HOME: String = "HomeScreen"
const val PANTRY: String = "PantryScreen"
const val BUDGET: String = "BudgetScreen"
const val ONBOARDING: String = "OnboardingScreen"
const val APP_SETTINGS: String = "AppSettingsScreen"
const val USER_PREFERENCES: String = "UserPreferencesScreen"

sealed class Screen(val route: String) {
	object Home: Screen(route = HOME)
	object Pantry: Screen(route = PANTRY)
	object Budget: Screen(route = BUDGET)
	object Onboarding: Screen(route = ONBOARDING)
	object AppSettings: Screen(route = APP_SETTINGS)
	object UserPreferences: Screen(route = USER_PREFERENCES)

	// TODO INDSÆT => object Profile: Screen(route = PROFILE) Måske.
	// TODO INDSÆT => object Shop: Screen(route = SHOPPING)
}