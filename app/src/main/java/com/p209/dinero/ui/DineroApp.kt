package com.p209.dinero.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.p209.dinero.R
import com.p209.dinero.core.data.util.NetworkMonitor
import com.p209.dinero.core.designsystem.component.DineroBackground
import com.p209.dinero.core.designsystem.component.DineroGradientBackground
import com.p209.dinero.core.designsystem.component.DineroNavigationBar
import com.p209.dinero.core.designsystem.component.DineroNavigationBarItem
import com.p209.dinero.core.designsystem.component.DineroNavigationRail
import com.p209.dinero.core.designsystem.component.DineroNavigationRailItem
import com.p209.dinero.core.designsystem.component.DineroTopAppBar
import com.p209.dinero.core.designsystem.icon.DineroIcons
import com.p209.dinero.core.designsystem.icon.Icon
import com.p209.dinero.core.designsystem.theme.GradientColors
import com.p209.dinero.core.designsystem.theme.LocalGradientColors
import com.p209.dinero.navigation.DineroNavHost
import com.p209.dinero.navigation.TopLevelDestination

@OptIn(ExperimentalComposeUiApi::class,
	ExperimentalLayoutApi::class,
	ExperimentalMaterial3Api::class
)
@Composable
fun DineroApp(
	windowSizeClass: WindowSizeClass,
	networkMonitor: NetworkMonitor,
	appState: DineroAppState = rememberDineroAppState(
		windowSizeClass = windowSizeClass,
		networkMonitor = networkMonitor
	)
) {
	val doShowGradientBackground =
		appState.currentTopLevelDestination == TopLevelDestination.HOME_TOP

	DineroBackground {
		DineroGradientBackground(
			gradientColors = if (doShowGradientBackground) LocalGradientColors.current else GradientColors()
		) {
			val snackbarHostState = remember { SnackbarHostState() }
			val isOffline by appState.isOffline.collectAsStateWithLifecycle()

			// Now in Android kommentar:
			// If user is not connected to the internet; show a snack bar to inform them.
			val notConnectedMessage = stringResource(R.string.user_is_offline)
			LaunchedEffect(isOffline) {
				if (isOffline) {
					snackbarHostState.showSnackbar(
						message = notConnectedMessage,
						duration = Indefinite
					)
				}
			}

			/* TODO
			if (appState.doShowSettingsDialog) {
				SettingsDialog(
					OnDismissListener = { appState.setShowSettingsDialog(false) }
				)
			} */

			Scaffold(
				modifier = Modifier.semantics {
					testTagsAsResourceId = true
				},
				containerColor = Color.Transparent,
				contentColor = MaterialTheme.colorScheme.onBackground,
				contentWindowInsets = WindowInsets(0, 0, 0, 0),
				snackbarHost = { SnackbarHost(snackbarHostState) },
				bottomBar = {
					if (appState.doShowBottomBar) {
						DineroBottomBar(
							destinations = appState.topLevelDestinations,
							onNavigateToDestination = appState::navigateToTopLevelDestination,
							currentDestination = appState.currentDestination,
							modifier = Modifier.testTag("DineroBottomBar") // TODO Kun nødvendig, hvis vi laver unit testing
						)
					}
				}
			) { padding ->
				Row(
					Modifier
						.fillMaxSize()
						.padding(padding)
						.consumeWindowInsets(padding)
						.windowInsetsPadding(
							WindowInsets.safeDrawing.only(
								WindowInsetsSides.Horizontal
							),
						),
				) {
					if (appState.doShowNavigationRail) {
						DineroNavRail(
							destinations = appState.topLevelDestinations,
							onNavigateToDestination = appState::navigateToTopLevelDestination,
							currentDestination = appState.currentDestination,
							modifier = Modifier
								.testTag("DineroNavRail") // TODO Kun nødvendig, hvis vi laver unit testing
								.safeDrawingPadding()
						)
					}

					Column(Modifier.fillMaxSize()) {
						// Show the top app bar on top level destinations.
						val destination = appState.currentTopLevelDestination

						if (destination != null) {
							DineroTopAppBar(
								titleResource = destination.titleTextId,
								actionIcon = DineroIcons.TEST_chefs_hat, // TODO Udskift TEST_chefs_hat
								actionIconContentDescription = null, // TODO Giv beskrivelse
								colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
									containerColor = Color.Transparent
								),
								onActionClick = { appState.setShowSettingsDialog(true) }
							)
						}

						DineroNavHost(appState.navController)
					}

					// Now in Android TODO
					// We may want to add padding or spacer when the snack bar is shown so that
					// content doesn't display behind it.
				}

			}
		}
	}
}

@Composable
private fun DineroNavRail(
	destinations: List<TopLevelDestination>,
	onNavigateToDestination: (TopLevelDestination) -> Unit,
	currentDestination: NavDestination?,
	modifier: Modifier = Modifier
) {
	DineroNavigationRail(modifier = modifier) {
		destinations.forEach { destination ->
			val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
			DineroNavigationRailItem(
				selected = selected,
				onClick = { onNavigateToDestination(destination) },
				icon = {
					val icon = if (selected) destination.selectedIcon else destination.unselectedIcon

					when (icon) {
						is Icon.ImageVectorIcon -> Icon(
							imageVector = icon.imageVector,
							contentDescription = null
						)

						is Icon.DrawableResourceIcon -> Icon(
							painter = painterResource(icon.id),
							contentDescription = null
						)
					}
				},
				label = { Text(stringResource(destination.iconTextID)) }
			)
		}
	}
}


@Composable
private fun DineroBottomBar(
	destinations: List<TopLevelDestination>,
	onNavigateToDestination: (TopLevelDestination) -> Unit,
	currentDestination: NavDestination?,
	modifier: Modifier = Modifier
) {
	DineroNavigationBar(
		modifier = modifier
	) {
		destinations.forEach { destination ->
			val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
			DineroNavigationBarItem(
				selected = selected,
				onClick = { onNavigateToDestination(destination) },
				icon = {
					val icon = if (selected) destination.selectedIcon else destination.unselectedIcon

					when (icon) {
						is Icon.ImageVectorIcon -> Icon(
							imageVector = icon.imageVector,
							contentDescription = null
						)

						is Icon.DrawableResourceIcon -> Icon(
							painter = painterResource(id = icon.id),
							contentDescription = null
						)
					}
				},
				label = { Text(stringResource(destination.iconTextID)) }
			)
		}
	}
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination): Boolean =
	this?.hierarchy?.any {
		it.route?.contains(destination.name, true) ?: false
	} ?: false