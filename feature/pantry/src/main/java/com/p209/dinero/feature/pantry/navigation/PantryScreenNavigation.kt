package com.p209.dinero.feature.pantry.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.p209.dinero.feature.pantry.PantryScreenRoute

const val PANTRY_SCREEN_NAVIGATION_ROUTE = "pantry_screen_route"

fun NavController.navigateToPantry(navOptions: NavOptions? = null) {
	this.navigate(PANTRY_SCREEN_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.pantryScreen() {
	composable(route = PANTRY_SCREEN_NAVIGATION_ROUTE) {
		PantryScreenRoute()
	}
}