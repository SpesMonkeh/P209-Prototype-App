package com.p209.dinero.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.p209.dinero.feature.home.HomeScreenRoute

const val HOME_SCREEN_NAVIGATION_ROUTE = "home_screen_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
	this.navigate(HOME_SCREEN_NAVIGATION_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen() =
	composable(route = HOME_SCREEN_NAVIGATION_ROUTE) {
		HomeScreenRoute()
	}