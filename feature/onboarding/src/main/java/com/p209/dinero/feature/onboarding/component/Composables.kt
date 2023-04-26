package com.p209.dinero.feature.onboarding.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

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
	textColor: Color = Color.White, // TODO Midlertidig farve
	fontSize: TextUnit = 26.sp,
) {
	Text(
		text = stringResource(textRes),
		modifier = modifier,
		fontSize = fontSize,
		color = textColor
	)
}