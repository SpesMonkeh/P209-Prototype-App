package com.p209.dinero.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.p209.dinero.core.common.navigation.DineoNavGraph
import com.p209.dinero.core.common.navigation.TopScreen
import com.p209.dinero.onboarding.OnboardingScreen

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
	this.navigate(DineoNavGraph.Onboarding.route, navOptions)
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