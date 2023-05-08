package com.p209.dinero.feature.onboarding.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingPage(
	page: Page,
	modifier: Modifier = Modifier,
){
	Column(
		modifier = modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Top,
	) {
		Image(
			modifier = modifier
				.fillMaxWidth(fraction = .5f)
				.fillMaxHeight(fraction = .7f),
			painter = painterResource(page.image),
			contentDescription = "Onboarding Page Image", // TODO fjern Onboarding Page Image og indsæt contentDescription.
		)

		Text(
			text = stringResource(page.title),
			modifier = modifier.fillMaxWidth(),
			fontSize = MaterialTheme.typography.headlineMedium.fontSize,
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center,
		)

		Text(
			text = stringResource(page.description),
			modifier = modifier
				.fillMaxWidth()
				.padding(horizontal = 40.dp)
				.padding(top = 20.dp),
			fontSize = MaterialTheme.typography.bodyLarge.fontSize,
			fontWeight = FontWeight.Medium,
			textAlign = TextAlign.Center,
		)
	}
}

@OnboardingPagePreview
@Composable
fun WelcomePagePreview() = PreviewPage(Page.Welcome)

@OnboardingPagePreview
@Composable
fun SelectLanguagePagePreview() = PreviewPage(Page.SelectLanguage)

@OnboardingPagePreview
@Composable
fun SetUsernamePagePreview() = PreviewPage(Page.SetUsername)

@Composable
fun PreviewPage(previewPage: Page) {
	Column(modifier = Modifier.fillMaxSize()) {
		OnboardingPage(page = previewPage)
	}
}

@Preview(
	showBackground = true,
	backgroundColor = 0xFF694FFF
)
annotation class OnboardingPagePreview