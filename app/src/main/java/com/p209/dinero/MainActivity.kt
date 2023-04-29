package com.p209.dinero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.p209.dinero.MainActivityUiState.Loading
import com.p209.dinero.MainActivityUiState.Success
import com.p209.dinero.core.data.util.NetworkMonitor
import com.p209.dinero.core.designsystem.theme.DineroTheme
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.ui.DineroAppMainComposable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject	lateinit var networkMonitor: NetworkMonitor

	val viewModel: MainActivityViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {

		// val splashScreen: SplashScreen = installSplashScreen()
		super.onCreate(savedInstanceState)

		var uiState: MainActivityUiState by mutableStateOf(Loading)

		updateUiState(
			lifecycleScope = lifecycleScope,
			lifecycle = lifecycle,
			viewModel = viewModel,
			onSetUiState = { uiState = it }
		)

		/* Keep the splash screen on-screen until the UI state is loaded. This condition is evaluated each time
		 * the app needs to be redrawn so it should be fast to avoid blocking the UI. */
		// splashScreen.setKeepOnScreenCondition {
		// 		when (uiState) {
		//			Loading -> true
		//			is Success -> false
		//

		/*  Turn off the decor fitting system windows, which allows us to handle insets, including IME animations */
		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			val systemUiController = rememberSystemUiController()
			val applyDarkTheme: Boolean = useDarkTheme(uiState)


			/* Update the dark content of the system bars to match the theme */
			DisposableEffect(systemUiController, applyDarkTheme) {
				systemUiController.systemBarsDarkContentEnabled = !applyDarkTheme
				onDispose { }
			}

			DineroTheme(
				darkTheme = applyDarkTheme,
				androidTheme = useAndroidTheme(uiState),
				disableDynamicTheming = disableDynamicTheming(uiState)
			) {
				DineroAppMainComposable(
					networkMonitor = networkMonitor,
					windowSizeClass = calculateWindowSizeClass(this)
				)
			}
		}
	}

	override fun onResume() {
		super.onResume()
	}

	override fun onPause() {
		super.onPause()
	}
}

private fun updateUiState(
	lifecycleScope: LifecycleCoroutineScope,
	lifecycle: Lifecycle,
	viewModel: MainActivityViewModel,
	onSetUiState: (MainActivityUiState) -> Unit,
) {
	lifecycleScope.launch {
		lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
			viewModel.uiState
				.onEach {
					onSetUiState(it)
				}
				.collect()
		}
	}
}

/**
 * **Now in Android dokumentation:**
 *
 * Returns `true` if the Android theme should be used, as a function of the [uiState].
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
 * **Now in Android dokumentation:**
 *
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
 * **Now in Android dokumentation:**
 *
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