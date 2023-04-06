package com.p209.dinero.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.p209.dinero.R
import com.p209.dinero.ui.components.MainScreenButton

@Composable
fun MainScreen(uiState: DineroUiState, contextPadding: PaddingValues) {
	Column {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.background(MaterialTheme.colorScheme.secondary)
				.padding(contextPadding),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = "Notifications"
			)
		}

		Spacer(Modifier.height(10.dp))

		Column(
			modifier = Modifier.fillMaxHeight()
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				MainScreenButton(
					label = stringResource(R.string.profil_DK),
					onClick = { /*TODO*/ }
				)
				MainScreenButton(
					label = stringResource(R.string.indstillinger_DK),
					onClick = { /*TODO*/ }
				)
			}

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				MainScreenButton(
					label = stringResource(R.string.koeleskab_DK),
					onClick = { /*TODO*/ }
				)
				MainScreenButton(
					label = stringResource(R.string.opskrifter_DK),
					onClick = { /*TODO*/ }
				)
			}

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				MainScreenButton(
					label = stringResource(R.string.budget_DK),
					onClick = { /*TODO*/ }
				)
				MainScreenButton(
					label = stringResource(R.string.tilbudsavis_DK),
					onClick = { /*TODO*/ }
				)
			}
		}
	}
}