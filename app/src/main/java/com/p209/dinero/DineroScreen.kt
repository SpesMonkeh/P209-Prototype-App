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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.p209.dinero.navigation.Screen
import com.p209.dinero.ui.DineroViewModel
import com.p209.dinero.ui.components.DineroNavigationBar

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
	val currentScreen = backStackEntry?.destination?.route ?: Screen.Home.route

	Scaffold(
		topBar = { DineroTopBar() },
		bottomBar = { DineroNavigationBar() },
		containerColor = MaterialTheme.colorScheme.onSecondaryContainer
	) { val uiState by viewModel.UiState.collectAsState()
	}
}