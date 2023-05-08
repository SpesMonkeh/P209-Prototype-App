package com.p209.dinero.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.p209.dinero.core.common.navigation.TopScreen
import com.p209.dinero.feature.home.HomeScreenRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
	this.navigate(TopScreen.Home.route, navOptions)

fun NavGraphBuilder.homeScreen() =
	composable(route = TopScreen.Home.route) { HomeScreenRoute() }