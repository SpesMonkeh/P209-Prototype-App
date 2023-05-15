package com.p209.dinero.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.p209.dinero.core.common.navigation.DineoNavGraph
import com.p209.dinero.feature.budget.navigation.budgetTopScreen
import com.p209.dinero.feature.home.navigation.homeNavGraph
import com.p209.dinero.feature.pantry.navigation.pantryTopScreen
import com.p209.dinero.onboarding.navigation.onboardingNavGraph

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
fun SetupNavGraph(
	navController: NavHostController,
	onSaveOnboardingState: (Boolean) -> Unit,
	startDestination: String = DineoNavGraph.Home.route
) {
	NavHost(
		navController = navController,
		startDestination = startDestination,
		route = DineoNavGraph.Root.route,
	) {
		homeNavGraph(navController)
		onboardingNavGraph(
			onSaveOnboardingState = onSaveOnboardingState,
			navController = navController)
		pantryTopScreen()
		budgetTopScreen()
	}
}