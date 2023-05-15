@file:OptIn(
	ExperimentalFoundationApi::class,
	ExperimentalAnimationApi::class,
	ExperimentalPagerApi::class,
)

package com.p209.dinero.onboarding

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.p209.dinero.core.designsystem.component.DineroBackground
import com.p209.dinero.core.designsystem.component.DineroGradientBackground
import com.p209.dinero.core.designsystem.theme.LocalGradientColors
import com.p209.dinero.feature.onboarding.R
import com.p209.dinero.onboarding.component.FinishOnboardingButton
import com.p209.dinero.onboarding.component.OnboardingPage
import com.p209.dinero.onboarding.navigation.Page

@Composable
internal fun OnboardingScreenRoute(
	navController: NavController,
	modifier: Modifier = Modifier,
	viewModel: OnboardingScreenViewModel
) {
	//OnboardingScreen(
	//	navController = navController,
	//	onboardingVM = viewModel,
	//	onboardingUiState = onboardingUiState,
	//	onVerifyUsername = viewModel::verifyUsername,
	//	onUpdateUsername = viewModel::setUsername,
	//	onSubmitClick = viewModel::dismissOnboarding,
	//	modifier = modifier,
	//)
}

@OptIn(ExperimentalPagerApi::class, ExperimentalLayoutApi::class)
@Composable
fun OnboardingScreen(
	navController: NavHostController,
	//onVerifyUsername: (String) -> Boolean,
	//onUpdateUsername: (String) -> Unit,
	//onSubmitClick: () -> Unit,
	onSaveOnboardingState: (Boolean) -> Unit,
	modifier: Modifier = Modifier,
) {
	val pages = listOf(
		Page.Welcome,
		Page.SelectLanguage,
		Page.SetUsername,
	)

	val onboardingPages = pages.size
	Log.d("Onboarding Screen", "Onboarding Welcome Page index: ${pages.indexOf(Page.Welcome)}")
	val pagerState = rememberPagerState(initialPage = pages.indexOf(Page.Welcome))
	Log.d("Onboarding Screen", "Pager state: $pagerState")

	//val isLoadingOnboarding: Boolean = onboardingUiState is OnboardingUiState.Loading
	//ReportDrawnWhen { !isLoadingOnboarding }

	DineroBackground {
		DineroGradientBackground(
			gradientColors = LocalGradientColors.current
		) {

			val snackbarHostState = remember { SnackbarHostState() }

			Scaffold(
				modifier = Modifier,
				containerColor = Color.Transparent,
				contentColor = MaterialTheme.colorScheme.onBackground,
				contentWindowInsets = WindowInsets(0, 0, 0, 0),
				snackbarHost = { SnackbarHost(snackbarHostState) },
			) { padding ->
				Column(
					modifier = modifier
						.fillMaxSize()
						.padding(padding)
						.consumeWindowInsets(padding)
						.windowInsetsPadding(
							WindowInsets.safeDrawing
								.only(
									WindowInsetsSides.Horizontal
								),
							),
				) {
					HorizontalPager(
						count = onboardingPages,
						state = pagerState,
						verticalAlignment = Alignment.Top,
					) { page ->
						Log.d("Onboarding Screen Horizontal Pager", "Position => $page OnboardingPage => ${pages[page]} ")
						OnboardingPage(page = pages[page])
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
						onSaveOnboardingState(true)
						navController.popBackStack()
					}
				}
			}
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