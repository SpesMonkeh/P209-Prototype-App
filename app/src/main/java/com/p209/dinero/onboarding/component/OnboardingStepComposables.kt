package com.p209.dinero.onboarding.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.p209.dinero.core.designsystem.R.drawable as DrawableR
import com.p209.dinero.core.designsystem.R.string as StringR

fun LazyGridScope.selectLanguageOnboardingStep(
	modifier: Modifier = Modifier
) {
	item(span = { GridItemSpan(maxLineSpan) }) {
		Column(
			verticalArrangement = Arrangement.spacedBy(20.dp),
		) {
			OnboardingStepText(
				textRes = StringR.info_select_language,
				modifier = modifier.align(Alignment.CenterHorizontally)
			)
			Row(
				horizontalArrangement = Arrangement.SpaceEvenly,
				modifier = modifier.fillMaxWidth()
			) {
				ImageVectorButton(
					imageVector = DrawableR.flag_dk,
					contentDescription = StringR.desc_DK_flag,
					buttonAction = { /*TODO*/ }
				)

				ImageVectorButton(
					imageVector = DrawableR.flag_uk,
					contentDescription = StringR.desc_EN_flag,
					buttonAction = { /*TODO*/ },
				)
			}

			Button(
				onClick = { /*TODO*/ },
				modifier = modifier.fillMaxWidth()
			) {
				Text(
					text = "DONE",
					color = Color(2)
				)
			}
		}
	}
}