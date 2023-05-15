package com.p209.dinero.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.p209.dinero.core.common.navigation.TopScreen
import com.p209.dinero.core.data.util.NetworkMonitor
import com.p209.dinero.feature.budget.navigation.navigateToBudget
import com.p209.dinero.feature.home.navigation.navigateToHome
import com.p209.dinero.feature.pantry.navigation.navigateToPantry
import com.p209.dinero.navigation.TopLevelDestination
import com.p209.dinero.navigation.TopLevelDestination.BUDGET_TOP_DESTINATION
import com.p209.dinero.navigation.TopLevelDestination.HOME_TOP_DESTINATION
import com.p209.dinero.navigation.TopLevelDestination.ONBOARDING_TOP_DESTINATION
import com.p209.dinero.navigation.TopLevelDestination.PANTRY_TOP_DESTINATION
import com.p209.dinero.onboarding.navigation.navigateToOnboarding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberDineroAppState(
	windowSizeClass: WindowSizeClass,
	networkMonitor: NetworkMonitor,
	coroutineScope: CoroutineScope = rememberCoroutineScope(),
	navController: NavHostController = rememberNavController()
): DineroAppState {

	// NavigationTrackingSideEffects(navController)

	return remember(
		windowSizeClass,
		coroutineScope,
		networkMonitor,
		navController
	) {
		DineroAppState(
			windowSizeClass,
			coroutineScope,
			networkMonitor,
			navController
		)
	}
}

@Stable
class DineroAppState(
	val windowSizeClass: WindowSizeClass,
	val coroutineScope: CoroutineScope,
	networkMonitor: NetworkMonitor,
	val navController: NavHostController,
) {
	val timeOut: Long = 5000L

	val currentDestination: NavDestination?
		@Composable get() {
			return navController.currentBackStackEntryAsState().value?.destination
		}

	val currentTopLevelDestination: TopLevelDestination
		@Composable get() = when (currentDestination?.route) {
			TopScreen.Home.route -> HOME_TOP_DESTINATION
			TopScreen.Pantry.route -> PANTRY_TOP_DESTINATION
			TopScreen.Budget.route -> BUDGET_TOP_DESTINATION
			TopScreen.Onboarding.route -> ONBOARDING_TOP_DESTINATION
			else -> HOME_TOP_DESTINATION
		}

	val doShowBottomBar: Boolean
		get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

	val doShowNavigationRail: Boolean
		get() = !doShowBottomBar

	val isOffline = networkMonitor.isOnline
		.map(Boolean::not)
		.stateIn(
			scope = coroutineScope,
			started = SharingStarted.WhileSubscribed(timeOut),
			initialValue = false
		)

	/** **Now in Android dokumentation:**
	 *
	 * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
	 * route.
	 */
	val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination
		.values()
		.asList()
		.filter { it.includeOnNavigationRail }

	var doShowSettingsDialog by mutableStateOf(false)
		private set

	/** **Now in Android dokumentation:**
	 *
	 * UI logic for navigating to a top level destination in the app. Top level destinations have
	 * only one copy of the destination of the back stack, and save and restore state whenever you
	 * navigate to and from it.
	 *
	 * @param topLevelDestination: The destination the app needs to navigate to.
	 */
	fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {

		trace("Navigation: ${topLevelDestination.name}") {
			val topLevelNavOptions = navOptions {

				/* Pop up to the start destination of the graph to avoid building up a large stack of destinations
				 * on the back stack as users select items */
				popUpTo(navController.graph.findStartDestination().id) { saveState = true }

				launchSingleTop = true // Avoid multiple copies of the same destination when reselecting the same item

				// Restore state when reselecting a previously selected item
				restoreState = true

			}
			when (topLevelDestination) {
				HOME_TOP_DESTINATION -> navController.navigateToHome(topLevelNavOptions)
				BUDGET_TOP_DESTINATION -> navController.navigateToBudget(topLevelNavOptions)
				PANTRY_TOP_DESTINATION -> navController.navigateToPantry(topLevelNavOptions)
				ONBOARDING_TOP_DESTINATION -> navController.navigateToOnboarding(topLevelNavOptions)

			}
		}
	}

	fun setShowSettingsDialog(doShow: Boolean) {
		doShowSettingsDialog = doShow
	}
}

// TODO Indsæt nedestående, om nødvendigt. Ellers slet.
/** **Now in Android dokumentation:**
 *
 * Stores information about navigation events to be used with JankStats
 *
 * `
 */
//@Composable
//private fun NavigationTrackingSideEffect(navController: NavHostController) {
//	TrackDisposableJank(navController) { metricsHolder ->
//		val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
//			metricsHolder.state?.putState("Navigation", destination.route.toString())
//		}
//
//		navController.addOnDestinationChangedListener(listener)
//
//		onDispose {
//			navController.removeOnDestinationChangedListener(listener)
//		}
//	}
//}

// data class DineroUiState( // TODO Tjek, om denne class er nødvendig
// 	val currentAppScreen: Screen = Screen.Home
// )