package com.p209.dinero.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.p209.dinero.feature.budget.navigation.budgetScreen
import com.p209.dinero.feature.home.navigation.homeScreen
import com.p209.dinero.feature.onboarding.navigation.onboardingScreenGraph
import com.p209.dinero.feature.pantry.navigation.pantryScreen

/**
 * **Now in Android dokumentation**
 *
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun MainNavHost(
	navController: NavHostController,
	startDestination: String,
	modifier: Modifier = Modifier,
) {
	NavHost(
		navController = navController,
		startDestination = startDestination,
		modifier = modifier
	) {
		onboardingScreenGraph(navController)
		homeScreen()
		budgetScreen()
		pantryScreen()
	}
}