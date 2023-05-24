package com.p209.dinero.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
internal fun HomeScreenRoute(
	navController: NavController,
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

	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(10.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
	) {
		Button(
			modifier = modifier,
			onClick = { /*TODO*/ }
		) {
			Text(
				text = "Budget",
				modifier = modifier
			)
		}
	}

}