package com.p209.dinero.feature.home

import androidx.activity.compose.ReportDrawnWhen
import androidx.annotation.StringRes
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.p209.dinero.core.designsystem.theme.DineroTheme

private val onboardingItemFontSize: TextUnit = 26.sp

@Composable
internal fun HomeScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: HomeScreenViewModel = hiltViewModel()
) {
	val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()

	HomeScreen(
		onboardingUiState = onboardingUiState,
		onVerifyUsername = viewModel::VerifyUsername,
		onUpdateUsername = viewModel::setUsername,
		onSubmitClick = viewModel::dismissOnboarding,
		modifier = modifier
	)
}

@Composable
fun HomeScreen(
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
				OnboardingItemText(
					textRes = R.string.info_select_language,
					modifier = modifier.align(CenterHorizontally)
				)

				IconButton(
					onClick = {},
					modifier = modifier,
					enabled = true,
					content = {	painterResource(R.drawable.flag_dk)	}
				)

				Divider(
					color = Color.LightGray, // TODO Midlertidig farve
					thickness = 5.dp,
					modifier = modifier.fillMaxWidth()
				)

				OnboardingItemText(
					textRes = R.string.info_enter_username,
					modifier = modifier.align(CenterHorizontally)
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
			}
		}
	}
}

@Composable
fun HomeScreenButton( // TODO Flyt til mere relevant script
	label: String,
	modifier: Modifier = Modifier,
	onClick: () -> Unit,
	fontSize: TextUnit = 24.sp,
	enabled: Boolean,
) {
	val shape = RoundedCornerShape(CornerSize(5))
	val colors = buttonColors(
		containerColor = colorScheme.primaryContainer,
		contentColor = colorScheme.onPrimaryContainer,
		disabledContainerColor = colorScheme.tertiaryContainer,
		disabledContentColor = colorScheme.onTertiaryContainer
	)

	Button(
		modifier = modifier,
		onClick = onClick,
		shape = shape,
		colors = colors,
		enabled = enabled
	) {
		Text(
			text = label,
			fontSize = fontSize,
			modifier = modifier
		)
	}
}

@Composable
private fun OnboardingItemText(
	@StringRes textRes: Int,
	modifier: Modifier = Modifier,
	fontSize: TextUnit = onboardingItemFontSize,
) {
	Text(
		text = stringResource(textRes),
		modifier = modifier,
		fontSize = fontSize
	)
}

@Composable
private fun getValidUsernameString(isValidUsername: Boolean) : String = when (isValidUsername) {
	true -> stringResource(R.string.textfieldLabel_valid_username)
	false -> stringResource(R.string.textfieldLabel_invalid_username)
}



@Preview
@Composable
fun PreviewHomeScreenButton() {
	var enableButton by remember { mutableStateOf(true) }
	DineroTheme {
		HomeScreenButton(
			label = "TEST",
			onClick = { enableButton = !enableButton},
			enabled = enableButton
		)
	}
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
	BoxWithConstraints {
		DineroTheme {
			HomeScreen(
				onboardingUiState = OnboardingUiState.Shown("Johnny Bib"),
				onVerifyUsername = { return@HomeScreen true },
				onUpdateUsername = { },
				onSubmitClick = { },
			)
		}
	}
}