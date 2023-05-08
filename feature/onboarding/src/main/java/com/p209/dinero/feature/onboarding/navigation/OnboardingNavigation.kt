package com.p209.dinero.feature.onboarding.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.p209.dinero.core.common.navigation.TopScreen
import com.p209.dinero.feature.onboarding.OnboardingScreen

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
	this.navigate(TopScreen.Onboarding.route, navOptions)
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.onboardingScreenGraph(
	navController: NavController,
) {
	navigation(route = TopScreen.Onboarding.route, startDestination = Screen.Welcome.route) {
		composable(route = Screen.Welcome.route) {
			OnboardingScreen(navController)
		}
	}
}