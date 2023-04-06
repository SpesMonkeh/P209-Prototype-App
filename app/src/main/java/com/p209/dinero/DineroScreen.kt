package com.p209.dinero

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.p209.dinero.ui.DineroViewModel
import com.p209.dinero.ui.MainScreen
import com.p209.dinero.ui.PantryScreen
import com.p209.dinero.ui.components.DineroNavigationBar
import com.p209.dinero.ui.theme.P209PrototypeAppTheme

enum class MainScreens {
	Main,
	Pantry,
	Budget,
	Settings,
	UserSettings,
	Discounts,
	Recipes
}

@Composable
fun DineroTopBar() {
	val user = "P209"

	Row(
		modifier = Modifier
			.background(Color(0xFF4FA23E))
			.fillMaxWidth()
			.padding(10.dp),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,

		) {
		Text(
			text = stringResource(R.string.greet_morning, user)
		)
	}
}

@Composable
fun DineroApp(modifier: Modifier = Modifier, viewModel: DineroViewModel = viewModel()) {
	val navController = rememberNavController()
	val backStackEntry by navController.currentBackStackEntryAsState()
	val currentScreen = MainScreens.valueOf(
		backStackEntry?.destination?.route ?: MainScreens.Main.name
	)

	Scaffold(
		topBar = { DineroTopBar() },
		bottomBar = { DineroNavigationBar() },
		containerColor = MaterialTheme.colorScheme.onSecondaryContainer
	) { contextPadding ->
		val uiState by viewModel.UiState.collectAsState()

		NavHost(
			navController = navController,
			startDestination = MainScreens.Main.name,
			modifier = modifier
		) {
			composable(route = MainScreens.Main.name) {
				MainScreen(
					uiState = uiState,
					contextPadding = contextPadding
				)
			}

			composable(route = MainScreens.Pantry.name) {
				PantryScreen(
					uiState = uiState,
					contextPadding = contextPadding,
					onReturnButtonClicked = { navController.popBackStack(
						route = MainScreens.Main.name,
						inclusive = false
					) }
				)
			}
		}
	}
}

@Preview(
	showBackground = true,
	name = "Main Screen"
)
@Composable
fun MainScreenPreview() {
	P209PrototypeAppTheme {
		DineroApp()
	}
}