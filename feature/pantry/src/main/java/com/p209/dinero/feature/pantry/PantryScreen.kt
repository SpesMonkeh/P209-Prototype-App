package com.p209.dinero.feature.pantry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun PantryScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: PantryScreenViewModel = hiltViewModel()
) {
	PantryScreen(
		modifier = modifier,
	)
}

@Composable
fun PantryScreen(
	modifier: Modifier = Modifier,
) {

	Column(
		modifier = modifier.fillMaxHeight(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
	}
}