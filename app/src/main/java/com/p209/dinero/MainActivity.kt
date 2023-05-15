package com.p209.dinero

import android.os.Bundle
import android.util.Log
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.p209.dinero.core.data.util.NetworkMonitor
import com.p209.dinero.core.designsystem.theme.DineroTheme
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.navigation.SetupNavGraph
import com.p209.dinero.ui.DineoPresentation
import com.p209.dinero.ui.rememberDineroAppState
import com.p209.dinero.viewModel.MainActivityUiState
import com.p209.dinero.viewModel.MainActivityUiState.Loading
import com.p209.dinero.viewModel.MainActivityUiState.Success
import com.p209.dinero.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@Inject	lateinit var networkMonitor: NetworkMonitor

	private val mainActivityVM: MainActivityViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {

		val splashScreen = installSplashScreen()

		super.onCreate(savedInstanceState)

		var uiState: MainActivityUiState by mutableStateOf(Loading)

		updateUiState(
			lifecycleScope = lifecycleScope,
			lifecycle = lifecycle,
			mainActivityVM = mainActivityVM,
			onSetUiState = { uiState = it }
		)

		splashScreen.setKeepOnScreenCondition {
			when(uiState) {
				Loading -> true
				is Success -> false
			}
		}

		/*  Turn off the decor fitting system windows, which allows us to handle insets, including IME animations */
		WindowCompat.setDecorFitsSystemWindows(window, false)

		setContent {
			val systemUiController = rememberSystemUiController()
			val applyDarkTheme: Boolean = useDarkTheme(uiState)
			val startScreen by mainActivityVM.StartDestination
			val onboardingCompleted = mainActivityVM.OnboardingCompleted

			Log.d("onCreate", "StartDestination => $startScreen")

			/* Update the dark content of the system bars to match the theme */
			DisposableEffect(systemUiController, applyDarkTheme) {
				systemUiController.systemBarsDarkContentEnabled = !applyDarkTheme
				onDispose { }
			}

			val windowSizeClass = calculateWindowSizeClass(this)

			DineroTheme(
				darkTheme = applyDarkTheme,
				androidTheme = useAndroidTheme(uiState),
				disableDynamicTheming = disableDynamicTheming(uiState)
			) {
				val appState = rememberDineroAppState(
					windowSizeClass = windowSizeClass,
					networkMonitor = networkMonitor,
				)

				SetupNavGraph(
					navController = appState.navController,
					onSaveOnboardingState = mainActivityVM::saveOnboardingState,
					startDestination = mainActivityVM.StartDestination.value
				)

				if (!onboardingCompleted.value) return@DineroTheme

				DineoPresentation(
					networkMonitor = networkMonitor,
					windowSizeClass = windowSizeClass,
					mainActivityVM = mainActivityVM,
					appState = appState
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
	mainActivityVM: MainActivityViewModel,
	onSetUiState: (MainActivityUiState) -> Unit,
) {
	lifecycleScope.launch {
		lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
			mainActivityVM.uiState
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