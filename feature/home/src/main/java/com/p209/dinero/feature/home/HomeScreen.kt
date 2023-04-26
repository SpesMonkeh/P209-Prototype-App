package com.p209.dinero.feature.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
internal fun HomeScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: HomeScreenViewModel = hiltViewModel()
) {

	HomeScreen(
		modifier = modifier
	)
}

@Composable
fun HomeScreen(
	modifier: Modifier = Modifier,
) {
	val snackbarHostState = remember { SnackbarHostState() }

	Scaffold(
		containerColor = Color.Transparent,
		contentColor = MaterialTheme.colorScheme.onBackground,
		contentWindowInsets = WindowInsets(0, 0, 0, 0),
		snackbarHost = { SnackbarHost(snackbarHostState) },
	) {

	}
}