package com.p209.dinero.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.p209.dinero.core.common.navigation.DineoNavGraph
import com.p209.dinero.core.common.navigation.TopScreen
import com.p209.dinero.feature.home.HomeScreenRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
	this.navigate(DineoNavGraph.Home.route, navOptions)

fun NavGraphBuilder.homeNavGraph(
	navController: NavHostController
) {
	navigation(
		startDestination = TopScreen.Home.route,
		route = DineoNavGraph.Home.route,
	) {
		composable(route = TopScreen.Home.route) {
			HomeScreenRoute(navController)
		}
	}
}