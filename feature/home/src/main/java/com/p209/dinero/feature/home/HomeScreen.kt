package com.p209.dinero.feature.home

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun HomeScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: HomeScreenViewModel = hiltViewModel()
) {
	val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()

	HomeScreen(
		homeScreenViewModel = viewModel,
		onboardingUiState = onboardingUiState,
		modifier = modifier
	)
}

@Composable
fun HomeScreen(
	homeScreenViewModel: HomeScreenViewModel,
	onboardingUiState: OnboardingUiState,
	modifier: Modifier = Modifier
) {
	val isLoadingOnboarding: Boolean = onboardingUiState is OnboardingUiState.Loading

	ReportDrawnWhen { !isLoadingOnboarding }

	LazyVerticalGrid(
		columns = GridCells.Adaptive(300.dp),
		contentPadding = PaddingValues(16.dp),
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		verticalArrangement = Arrangement.spacedBy(24.dp),
		modifier =  modifier.fillMaxSize(),
	) {
		onboarding(
			onboardingUiState = onboardingUiState,
			onVerifyNewUsername = { homeScreenViewModel.VerifyNewUsername(it) },
			onUpdateUserName = homeScreenViewModel::setUsername,
			onSubmitClick = homeScreenViewModel::dismissOnboarding,
			modifier = modifier
		)

	}
}

private fun LazyGridScope.onboarding(
	onboardingUiState: OnboardingUiState,
	onVerifyNewUsername: (String) -> Boolean,
	onUpdateUserName: (String) -> Unit,
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
			var validNewUsername: Boolean by remember { mutableStateOf(false) }
			val focusManager = LocalFocusManager.current

			Column(
				verticalArrangement = Arrangement.spacedBy(24.dp),
			) {
				Text(
					text = "Please enter a username",
					fontSize = 45.sp,
					modifier = modifier.align(Alignment.CenterHorizontally)
				)
				OutlinedTextField(
					value = tempUserName,
					singleLine = true,
					modifier = Modifier.fillMaxWidth(),
					onValueChange = { enteredUsername ->
						validNewUsername = onVerifyNewUsername(enteredUsername)
						if (!validNewUsername) return@OutlinedTextField
						tempUserName = enteredUsername
					},
					label = {
						if (validNewUsername) Text("Press Done to continue")
						else Text("Username is not valid")
					},
					isError = !validNewUsername,
					keyboardOptions = KeyboardOptions.Default.copy(
						imeAction = ImeAction.Done
					),
					keyboardActions = KeyboardActions(onDone = {
						if (validNewUsername)
							onUpdateUserName(tempUserName)
						focusManager.clearFocus()
					}),
				)
			}
			Button(
				onClick = onSubmitClick,
				enabled = validNewUsername,
				modifier = modifier,
				content = {
					Text("Done")
				}
			)
		}
	}
}


@Composable
fun MainScreenButton( // TODO Flyt til mere relevant script
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