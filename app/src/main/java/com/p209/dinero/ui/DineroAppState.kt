package com.p209.dinero.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.p209.dinero.core.data.util.NetworkMonitor
import com.p209.dinero.feature.budget.navigation.BUDGET_SCREEN_NAVIGATION_ROUTE
import com.p209.dinero.feature.home.navigation.HOME_SCREEN_NAVIGATION_ROUTE
import com.p209.dinero.feature.pantry.navigation.PANTRY_SCREEN_NAVIGATION_ROUTE
import com.p209.dinero.navigation.Screen
import com.p209.dinero.navigation.TopLevelDestination
import com.p209.dinero.navigation.TopLevelDestination.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberDineroAppState(
	windowSizeClass: WindowSizeClass,
	networkMonitor: NetworkMonitor,
	coroutineScope: CoroutineScope = rememberCoroutineScope(),
	navController: NavHostController = rememberNavController(),
): DineroAppState {
	// Kode fra Now in Android på linjen herunder
	// NavigationTrackingSideEffects(navController)

	return remember(windowSizeClass, coroutineScope, networkMonitor, navController) {
		DineroAppState(windowSizeClass, coroutineScope,	networkMonitor,	navController)
	}
}

data class DineroUiState(
	val currentAppScreen: Screen = Screen.Home
)

@Stable
class DineroAppState(
	val windowSizeClass: WindowSizeClass,
	val coroutineScope: CoroutineScope,
	networkMonitor: NetworkMonitor,
	val navController: NavHostController,
) {
	val currentDestination: NavDestination?
		@Composable get() = navController
			.currentBackStackEntryAsState().value?.destination

	val currentTopLevelDestination: TopLevelDestination?
		@Composable get() = when (currentDestination?.route) {
			HOME_SCREEN_NAVIGATION_ROUTE -> HOME_TOP
			PANTRY_SCREEN_NAVIGATION_ROUTE -> PANTRY_TOP
			BUDGET_SCREEN_NAVIGATION_ROUTE -> BUDGET_TOP
		}
}
