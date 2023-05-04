package com.p209.dinero.core.model.data

/** *Data Class*
 *
 * Defines the basic user data values, i.e. username, theme etc..
 * If the user should be able to modify a given value, do so through
 * [com.p209.dinero.feature.settings.UserEditableSettings].
 */
data class UserData(
	val username: String?, // TODO Bør måske opbevares andetsteds og skal den være nullable?
	val useDynamicColor: Boolean,
	val onboardingCompleted: Boolean,
	val darkThemeConfig: DarkThemeConfig,
	val themeBrand: ThemeBrand
)