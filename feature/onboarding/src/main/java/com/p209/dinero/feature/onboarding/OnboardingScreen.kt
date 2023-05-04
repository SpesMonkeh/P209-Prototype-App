@file:OptIn(ExperimentalPagerApi::class,
	ExperimentalFoundationApi::class,
	ExperimentalPagerApi::class,
	ExperimentalAnimationApi::class
)

package com.p209.dinero.feature.onboarding

import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.p209.dinero.core.model.data.Screen
import com.p209.dinero.feature.onboarding.component.FinishOnboardingButton
import com.p209.dinero.feature.onboarding.pages.OnboardingPage

@Composable
internal fun OnboardingScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: OnboardingScreenViewModel = hiltViewModel(),
) {
	val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()

	OnboardingScreen(
		viewModel = viewModel,
		onboardingUiState = onboardingUiState,
		//onVerifyUsername = viewModel::verifyUsername,
		//onUpdateUsername = viewModel::setUsername,
		//onSubmitClick = viewModel::dismissOnboarding,
		modifier = modifier,
	)
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
	viewModel: OnboardingScreenViewModel,
	onboardingUiState: OnboardingUiState,
	//onVerifyUsername: (String) -> Boolean,
	//onUpdateUsername: (String) -> Unit,
	//onSubmitClick: () -> Unit,
	modifier: Modifier = Modifier,
	navController: NavHostController = rememberNavController(),
) {

	val onboardingPages = viewModel.pages.size
	val pagerState = rememberPagerState()

	val isLoadingOnboarding: Boolean = onboardingUiState is OnboardingUiState.Loading

	ReportDrawnWhen { !isLoadingOnboarding }

	Column(modifier = modifier.fillMaxSize()) {
		HorizontalPager(
			count = onboardingPages,
			state = pagerState,
			verticalAlignment = Alignment.Top,
			modifier = modifier.weight(10f),
		) { position ->
			OnboardingPage(page = viewModel.pages[position])
		}
		HorizontalPagerIndicator(
			modifier = modifier
				.align(Alignment.CenterHorizontally)
				.weight(1f),
			pagerState = pagerState
		)
		FinishOnboardingButton(
			text = "Finish",
			pagerState = pagerState,
			modifier = modifier.weight(1f),
		) {
			viewModel.saveOnboardingState(completed = true)
			navController.popBackStack()
			navController.navigate(Screen.Home.route)
		}
	}
}

//private fun LazyGridScope.onboarding(
//	onboardingUiState: OnboardingUiState,
//	//onVerifyUsername: (String) -> Boolean,
//	//onUpdateUsername: (String) -> Unit,
//	//onSubmitClick: () -> Unit,
//	modifier: Modifier = Modifier
//) = when (onboardingUiState) {
//	OnboardingUiState.Loading,
//	OnboardingUiState.LoadFailed,
//	OnboardingUiState.Hidden,
//	-> Unit
//
//	is OnboardingUiState.Shown -> {
//		item(span = { GridItemSpan(maxLineSpan) }) {
//
//			var tempUserName: String by remember { mutableStateOf("") }
//			var validUsername: Boolean by remember { mutableStateOf(false) }
//			val focusManager = LocalFocusManager.current
//
//			Column(
//				verticalArrangement = Arrangement.spacedBy(20.dp),
//			) {
//				SectionDivider()
//
//				OnboardingStepText(
//					textRes = R.string.info_enter_username,
//					modifier = modifier.align(Alignment.CenterHorizontally)
//				)
//
//				OutlinedTextField(
//					value = tempUserName,
//					singleLine = true,
//					modifier = Modifier.fillMaxWidth(),
//					onValueChange = { enteredUsername ->
//						validUsername = onVerifyUsername(enteredUsername)
//						if (!validUsername) return@OutlinedTextField
//						tempUserName = enteredUsername
//					},
//					label = { Text(getValidUsernameString(validUsername)) },
//					isError = !validUsername,
//					keyboardOptions = KeyboardOptions.Default.copy(
//						imeAction = ImeAction.Done
//					),
//					keyboardActions = KeyboardActions(onDone = {
//						if (validUsername)
//							onUpdateUsername(tempUserName)
//						focusManager.clearFocus()
//					}),
//				)
//
//				Row(
//					verticalAlignment = Alignment.CenterVertically,
//					horizontalArrangement = Arrangement.End,
//					modifier = modifier.fillMaxWidth()
//				) {
//					Button(
//						onClick = onSubmitClick,
//						enabled = validUsername,
//						content = { Text(stringResource(R.string.submitButton_done)) }
//					)
//				}
//
//				SectionDivider()
//			}
//		}
//	}
//}

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