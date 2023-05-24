package com.p209.dinero.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.p209.dinero.core.common.navigation.DineoNavGraph
import com.p209.dinero.core.common.navigation.TopScreen
import com.p209.dinero.feature.budget.navigation.budgetTopScreen
import com.p209.dinero.feature.home.navigation.homeNavGraph
import com.p209.dinero.feature.pantry.navigation.pantryTopScreen
import com.p209.dinero.onboarding.OnboardingScreen
import javax.inject.Singleton

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
@Singleton
fun SetupNavGraph(
	navController: NavHostController,
	onSaveOnboardingState: (Boolean) -> Unit,
	startDestination: String = DineoNavGraph.Home.route,
) {
	NavHost(
		navController = navController,
		startDestination = startDestination,
		route = DineoNavGraph.Root.route,
	) {
		mainNavGraph(navController)
	}
}

@Composable
fun MainNavHost(
	navController: NavHostController,
	startDestination: String = DineoNavGraph.Home.route,
	route: String = DineoNavGraph.Root.route,
) {
	NavHost(
		startDestination = startDestination,
		navController = navController,
		route = route
	) {
		mainNavGraph(
			navController = navController
		)
	}
}

@Composable
fun OnboardingNavHost(
	navController: NavHostController,
	onSaveOnboardingState: (Boolean) -> Unit,
	startDestination: String = DineoNavGraph.Onboarding.route,
	route: String = DineoNavGraph.Root.route,
) {
	NavHost(
		startDestination = startDestination,
		navController = navController,
		route = route
	) {
		onboardingNavGraph(
			onSaveOnboardingState = onSaveOnboardingState,
			navController = navController)
	}
}

fun NavGraphBuilder.mainNavGraph(
	navController: NavHostController,
	startDestination: String = DineoNavGraph.Home.route
) {
	homeNavGraph(navController)
	pantryTopScreen()
	budgetTopScreen()
}

fun NavGraphBuilder.onboardingNavGraph(
	onSaveOnboardingState: (Boolean) -> Unit,
	navController: NavHostController,
) {
	navigation(
		startDestination = TopScreen.Onboarding.route,
		route = DineoNavGraph.Onboarding.route,
	) {
		composable(route = TopScreen.Onboarding.route) {
			OnboardingScreen(
				navController = navController,
				onSaveOnboardingState = onSaveOnboardingState
			)
		}
	}
}

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
	this.navigate(DineoNavGraph.Onboarding.route, navOptions)
}