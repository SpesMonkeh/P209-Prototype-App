package com.p209.dinero.feature.home.component

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.p209.dinero.core.designsystem.theme.DineroTheme

@Composable
fun HomeScreenButton( // TODO Flyt til mere relevant script
	label: String,
	modifier: Modifier = Modifier,
	onClick: () -> Unit,
	fontSize: TextUnit = 24.sp,
	enabled: Boolean,
) {
	val shape = RoundedCornerShape(CornerSize(5))
	val colors = ButtonDefaults.buttonColors(
		containerColor = MaterialTheme.colorScheme.primaryContainer,
		contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
		disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
		disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
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