@file:OptIn(
	ExperimentalPagerApi::class,
	ExperimentalAnimationApi::class
)

package com.p209.dinero.onboarding.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.p209.dinero.core.designsystem.theme.DineroTheme

/** *Composable*
 *
 */
@Composable
fun ImageVectorButton(
	@DrawableRes imageVector: Int,
	@StringRes contentDescription: Int,
	buttonAction: () -> Unit,
	modifier: Modifier = Modifier,
	contentScale: ContentScale = ContentScale.FillBounds,
	alpha: Float = 1f
) {
	Image(
		imageVector = ImageVector.vectorResource(imageVector),
		contentDescription = stringResource(contentDescription),
		contentScale = contentScale,
		alpha = alpha,
		modifier = modifier.clickable { buttonAction() }
	)
}

@Composable
fun OnboardingStepText(
	@StringRes textRes: Int,
	modifier: Modifier = Modifier,
	textColor: Color = Color.White,
	fontSize: TextUnit = 26.sp,
) {
	Text(
		text = stringResource(textRes),
		modifier = modifier,
		fontSize = fontSize,
		color = textColor
	)
}

@Composable
fun FinishOnboardingButton(
	text: String,
	pagerState: PagerState,
	modifier: Modifier = Modifier,
	fontSize: TextUnit = 26.sp,
	textColor: Color = Color.White,
	buttonColor: Color = Color.Black,
	onClick: ()-> Unit = { },
) {
	val lastPage = pagerState.pageCount - 1

	Row(
		modifier = modifier.padding(horizontal = 40.dp),
		verticalAlignment = Alignment.Top,
		horizontalArrangement = Arrangement.Center,
	) {
		AnimatedVisibility(
			modifier = modifier.fillMaxWidth(),
			visible = pagerState.currentPage == lastPage,
		) {
			Button(
				onClick = onClick,
				colors = ButtonDefaults.buttonColors(
					contentColor = textColor,
					containerColor = buttonColor,
				),
			) {
				Text(
					text = text,
					modifier = modifier,
					fontSize = fontSize,
					color = textColor,
				)
			}
		}
	}
}

@Preview
@Composable
fun FinishOnboardingButtonPreview() {
	DineroTheme {
		FinishOnboardingButton(
			text = "Finish",
			pagerState = rememberPagerState(),
		)
	}
}