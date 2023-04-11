package com.p209.dinero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.p209.dinero.MainActivityUiState.Loading
import com.p209.dinero.MainActivityUiState.Success
import com.p209.dinero.core.data.util.NetworkMonitor
import com.p209.dinero.core.designsystem.theme.DineroTheme
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.ui.DineroApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

	@Inject
	lateinit var networkMonitor: NetworkMonitor

	val viewModel: MainActivityViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {

		// val splashScreen: SplashScreen = installSplashScreen()
		super.onCreate(savedInstanceState)

		var uiState: MainActivityUiState by mutableStateOf(Loading)

		// Kommentar fra Now in Android:
		// Update the UiState
		lifecycleScope.launch {
			lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.uiState
					.onEach {
						uiState = it
					}
					.collect()
			}
		}

		// Now in Android kommentar:
		// Keep the splash screen on-screen until the UI state is loaded. This condition is
		// evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
		// the UI.

		/* splashScreen.setKeepOnScreenCondition {
		*  		when (uiState) {
		* 			Loading -> true
		* 			is Success -> false
		*/

		setContent {
			val systemUiController = rememberSystemUiController()
			val applyDarkTheme: Boolean = useDarkTheme(uiState)

			/*
			*	Now in Android kommentar:
			*	Update the dark content of the system bars to match the theme
			*/
			DisposableEffect(
				key1 = systemUiController,
				key2 = applyDarkTheme
			) {
				systemUiController.systemBarsDarkContentEnabled = !applyDarkTheme
				onDispose { }
			}

			DineroTheme(
				darkTheme = applyDarkTheme,
				androidTheme = useAndroidTheme(uiState),
				disableDynamicTheming = disableDynamicTheming(uiState)
			) {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					DineroApp(
						networkMonitor = networkMonitor,
						windowSizeClass = calculateWindowSizeClass(this)
					)
				}
			}
		}
	}
}

/**
 * Dokumentation fra Now in Android:
 * ```
 * Returns<br/> `true` if the Android theme should be used, as a function of the [uiState].
 */
@Composable
private fun useAndroidTheme(
	uiState: MainActivityUiState
): Boolean = when (uiState) {
	Loading -> false
	is Success -> when (uiState.userData.themeBrand) {
		ThemeBrand.DEFAULT -> false
		ThemeBrand.ANDROID -> true
	}
}

/**
 * Dokumentation fra Now in Android:
 *```
 * Returns `true` if the dynamic color is disabled, as a function of the [uiState].
 */
@Composable
private fun disableDynamicTheming(
	uiState: MainActivityUiState
): Boolean = when (uiState) {
	Loading -> false
	is Success -> !uiState.userData.useDynamicColor
}

/**
 * Dokumentation fra Now in Android:
 *```
 * Returns `true` if dark theme should be used, as a function of the [uiState] and the
 * current system context.
 */
@Composable
private fun useDarkTheme(
	uiState: MainActivityUiState
): Boolean = when (uiState) {
	Loading -> isSystemInDarkTheme()
	is Success -> when (uiState.userData.darkThemeConfig) {
		DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
		DarkThemeConfig.LIGHT -> false
		DarkThemeConfig.DARK -> true
	}
}