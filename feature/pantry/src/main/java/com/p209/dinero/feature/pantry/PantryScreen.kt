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
import com.p209.dinero.feature.pantry.R.string

@Composable
fun PantryScreen(
	/* uiState: DineroUiState, */
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
				text = stringResource(string.return_to_main),
				fontSize = 24.sp,
				modifier = modifier
			)
		}
	}
}