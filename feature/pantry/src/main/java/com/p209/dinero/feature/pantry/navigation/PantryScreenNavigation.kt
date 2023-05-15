package com.p209.dinero.feature.pantry.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.p209.dinero.core.common.navigation.TopScreen
import com.p209.dinero.feature.pantry.PantryScreenRoute

fun NavController.navigateToPantry(navOptions: NavOptions? = null) {
	this.navigate(route = TopScreen.Pantry.route, navOptions)
}

fun NavGraphBuilder.pantryTopScreen() {
	composable(route = TopScreen.Pantry.route) {
		PantryScreenRoute()
	}
}