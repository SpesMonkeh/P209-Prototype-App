package com.p209.dinero.feature.onboarding.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.p209.dinero.feature.onboarding.OnboardingScreenRoute

const val ONBOARDING_SCREEN_NAVIGATION_ROUTE = "onboarding_screen_route"

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) =
	this.navigate(ONBOARDING_SCREEN_NAVIGATION_ROUTE, navOptions)

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.onboardingScreen() =
	composable(route = ONBOARDING_SCREEN_NAVIGATION_ROUTE) {
		OnboardingScreenRoute()
	}