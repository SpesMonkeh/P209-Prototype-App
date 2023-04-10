package com.p209.dinero.feature.pantry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun PantryScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: PantryScreenViewModel = viewModel()
) {

}

@Composable
fun PantryScreen(
	contextPadding: PaddingValues,
	modifier: Modifier = Modifier,
	onReturnButtonClicked: () -> Unit = {}
) {

	Column(
		modifier = modifier
			.fillMaxHeight()
			.padding(contextPadding),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Button(
			modifier = modifier,
			onClick = onReturnButtonClicked,
		) {
			Text(
				text = stringResource(R.string.return_to_home_screen),
				fontSize = 24.sp,
				modifier = modifier
			)
		}
	}
}