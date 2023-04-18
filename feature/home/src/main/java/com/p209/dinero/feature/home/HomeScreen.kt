package com.p209.dinero.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

}