package com.p209.dinero.navigation

const val HOME: String = "Dinero"
const val PANTRY: String = "Pantry"
const val BUDGET: String = "Budget"

sealed class Screen(val route: String) {
	object Home: Screen(HOME) // TODO Hent String fra XML-resource i stedet for at bruge const val string.
	object Pantry: Screen(PANTRY)
	object Budget: Screen(BUDGET)


	// TODO INDSÆT => object Settings: Screen(settingsR.string.settings)
	// TODO INDSÆT => object Profile: Screen(profileR.string.profile)
	// TODO INDSÆT => object Shop: Screen(shopR.string.shop)
}