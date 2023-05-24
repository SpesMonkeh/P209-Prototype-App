package com.p209.dinero.ui


import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.p209.dinero.core.data.util.NetworkMonitor
import com.p209.dinero.core.designsystem.component.DineoBackground
import com.p209.dinero.core.designsystem.component.DineoGradientBackground
import com.p209.dinero.core.designsystem.theme.LocalGradientColors
import com.p209.dinero.navigation.OnboardingNavHost
import com.p209.dinero.viewModel.MainActivityViewModel


@OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun OnboardingView(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    mainActivityVM: MainActivityViewModel,
    appState: DineoAppState,
    navController: NavHostController,
    onSaveOnboardingState: (Boolean) -> Unit,
) {
    DineoBackground {
        DineoGradientBackground(
            gradientColors = LocalGradientColors.current,
        ) {

            val snackbarHostState = remember { SnackbarHostState() }

            ShowNoInternetSnackbarIf(appState.isOffline, snackbarHostState)
            ShowSettingsDialog(appState)

            Scaffold(
                modifier = Modifier,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = { SnackbarHost(snackbarHostState) },
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
                }
            }
            OnboardingNavHost(
                navController = navController,
                onSaveOnboardingState = onSaveOnboardingState,
            )
        }
    }
}