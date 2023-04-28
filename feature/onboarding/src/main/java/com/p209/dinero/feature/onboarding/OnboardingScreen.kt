package com.p209.dinero.feature.onboarding

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.p209.dinero.core.designsystem.theme.DineroTheme
import com.p209.dinero.feature.onboarding.component.OnboardingStepText
import com.p209.dinero.feature.onboarding.component.selectLanguageOnboardingStep

@Composable
internal fun OnboardingScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: OnboardingScreenViewModel = hiltViewModel(),
) {
	val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()

	OnboardingScreen(
		onboardingUiState = onboardingUiState,
		onVerifyUsername = viewModel::verifyUsername,
		onUpdateUsername = viewModel::setUsername,
		onSubmitClick = viewModel::dismissOnboarding,
		modifier = modifier,
	)
}

@Composable
fun OnboardingScreen(
	onboardingUiState: OnboardingUiState,
	onVerifyUsername: (String) -> Boolean,
	onUpdateUsername: (String) -> Unit,
	onSubmitClick: () -> Unit,
	modifier: Modifier = Modifier,
) {

	val isLoadingOnboarding: Boolean = onboardingUiState is OnboardingUiState.Loading

	ReportDrawnWhen { !isLoadingOnboarding }

	LazyVerticalGrid(
		columns = GridCells.Adaptive(300.dp),
		contentPadding = PaddingValues(16.dp),
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		verticalArrangement = Arrangement.Center,
		modifier =  modifier.fillMaxSize(),
	) {
		selectLanguageOnboardingStep(
			modifier = modifier,
		)
		onboarding(
			onboardingUiState = onboardingUiState,
			onVerifyUsername = onVerifyUsername,
			onUpdateUsername = onUpdateUsername,
			onSubmitClick = onSubmitClick,
			modifier = modifier
		)
	}
}

private fun LazyGridScope.onboarding(
	onboardingUiState: OnboardingUiState,
	onVerifyUsername: (String) -> Boolean,
	onUpdateUsername: (String) -> Unit,
	onSubmitClick: () -> Unit,
	modifier: Modifier = Modifier
) = when (onboardingUiState) {
	OnboardingUiState.Loading,
	OnboardingUiState.LoadFailed,
	OnboardingUiState.Hidden,
	-> Unit

	is OnboardingUiState.Shown -> {
		item(span = { GridItemSpan(maxLineSpan) }) {

			var tempUserName: String by remember { mutableStateOf("") }
			var validUsername: Boolean by remember { mutableStateOf(false) }
			val focusManager = LocalFocusManager.current

			Column(
				verticalArrangement = Arrangement.spacedBy(20.dp),
			) {
				SectionDivider()

				OnboardingStepText(
					textRes = R.string.info_enter_username,
					modifier = modifier.align(Alignment.CenterHorizontally)
				)

				OutlinedTextField(
					value = tempUserName,
					singleLine = true,
					modifier = Modifier.fillMaxWidth(),
					onValueChange = { enteredUsername ->
						validUsername = onVerifyUsername(enteredUsername)
						if (!validUsername) return@OutlinedTextField
						tempUserName = enteredUsername
					},
					label = { Text(getValidUsernameString(validUsername)) },
					isError = !validUsername,
					keyboardOptions = KeyboardOptions.Default.copy(
						imeAction = ImeAction.Done
					),
					keyboardActions = KeyboardActions(onDone = {
						if (validUsername)
							onUpdateUsername(tempUserName)
						focusManager.clearFocus()
					}),
				)

				Row(
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.End,
					modifier = modifier.fillMaxWidth()
				) {
					Button(
						onClick = onSubmitClick,
						enabled = validUsername,
						content = { Text(stringResource(R.string.submitButton_done)) }
					)
				}

				SectionDivider()
			}
		}
	}
}

@Composable
private fun SectionDivider(
	modifier: Modifier = Modifier,
	color: Color = Color.LightGray, // TODO Midlertidig farve
	thickness: Dp = 5.dp,
) {
	Divider(
		color = color,
		thickness = thickness,
		modifier = modifier.fillMaxWidth()
	)
}

@Composable
private fun getValidUsernameString(isValidUsername: Boolean) : String = when (isValidUsername) {
	true -> stringResource(R.string.textfieldLabel_valid_username)
	false -> stringResource(R.string.textfieldLabel_invalid_username)
}

@Preview(
	backgroundColor = 0xFF0E003D,
	showBackground = true
)
@Composable
fun PreviewOnboardingScreen() {
	BoxWithConstraints {
		DineroTheme {
			OnboardingScreen(
				onboardingUiState = OnboardingUiState.Shown("Johnny Bib"),
				onVerifyUsername = { return@OnboardingScreen true },
				onUpdateUsername = { },
				onSubmitClick = { },
			)
		}
	}
}