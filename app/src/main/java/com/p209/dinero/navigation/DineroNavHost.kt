package com.p209.dinero.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.p209.dinero.feature.home.navigation.HOME_SCREEN_NAVIGATION_ROUTE
import com.p209.dinero.feature.home.navigation.homeScreen

/**
 * **Dokumentation fra Now in Android:**
 * ```
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun DineroNavHost(
	navController: NavHostController,
	modifier: Modifier = Modifier,
	startDestination: String = HOME_SCREEN_NAVIGATION_ROUTE
) {
	NavHost(
		navController = navController,
		startDestination = startDestination,
		modifier = modifier
	) {
		homeScreen()
	}
}