package com.p209.dinero.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun HomeScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: HomeScreenViewModel = viewModel()
) {
	val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()
	HomeScreen(onboardingUiState, modifier)
}

@Composable
fun HomeScreen(
	// uiState: DineroUiState,
	onboardingUiState: OnboardingUiState,
	modifier: Modifier = Modifier
) {
	Column {
		Row(
			modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondary).padding(10.dp),
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
					label = stringResource(R.string.profile),
					onClick = { /*TODO*/ }
				)
				MainScreenButton(
					label = stringResource(R.string.settings),
					onClick = { /*TODO*/ }
				)
			}

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				MainScreenButton(
					label = stringResource(R.string.pantry),
					onClick = { /*TODO*/ }
				)
				MainScreenButton(
					label = stringResource(R.string.recipes),
					onClick = { /*TODO*/ }
				)
			}

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				MainScreenButton(
					label = stringResource(R.string.budget),
					onClick = { /*TODO*/ }
				)
				MainScreenButton(
					label = stringResource(R.string.shop),
					onClick = { /*TODO*/ }
				)
			}
		}
	}
}

@Composable
fun MainScreenButton(
	label: String,
	modifier: Modifier = Modifier,
	onClick: () -> Unit,
	fontSize: TextUnit = 24.sp,
	color: Color = Color.LightGray
) {
	Button(
		modifier = modifier,
		onClick = onClick,
	) {
		Text(
			text = label,
			fontSize = fontSize,
			modifier = modifier
		)
	}
}