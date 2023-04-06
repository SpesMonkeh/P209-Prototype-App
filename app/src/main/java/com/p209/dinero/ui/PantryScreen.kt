package com.p209.dinero.ui

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
import androidx.compose.ui.unit.sp

@Composable
fun PantryScreen(
	uiState: DineroUiState,
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
				text = "Return To Main",
				fontSize = 24.sp,
				modifier = modifier
			)
		}
	}
}