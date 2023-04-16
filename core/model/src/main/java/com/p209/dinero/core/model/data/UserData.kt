package com.p209.dinero.core.model.data

/** *Data Class*
 *
 *  Indeholder brugerens data.
 *  Hvis brugeren skal kunne justere en given værdi, så bør dette ske via
 *  [com.p209.dinero.feature.settings.UserEditableSettings].
 *
 *  `
 */
data class UserData(
	val username: String?, // TODO Bør måske opbevares andetsteds og skal den være nullable?
	val useDynamicColor: Boolean,
	val hideOnboarding: Boolean,
	val darkThemeConfig: DarkThemeConfig,
	val themeBrand: ThemeBrand
)