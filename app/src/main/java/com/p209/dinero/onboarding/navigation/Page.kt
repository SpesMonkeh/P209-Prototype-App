package com.p209.dinero.onboarding.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.p209.dinero.core.designsystem.R.drawable as DrawableR
import com.p209.dinero.core.designsystem.R.string as StringR

sealed class Page(
	@DrawableRes val image: Int,
	@StringRes val title: Int,
	@StringRes val description: Int,
) {
	object Welcome: Page(
		image = DrawableR.missing_image256,
		title = StringR.title_welcome,
		description = StringR.description_welcome,
	)

	object SelectLanguage: Page(
		image = DrawableR.missing_image256,
		title = StringR.title_selectLanguage,
		description = StringR.description_selectLanguage,
	)

	object SetUsername: Page(
		image = DrawableR.missing_image256,
		title = StringR.title_setUsername,
		description = StringR.description_setUsername,
	)
}