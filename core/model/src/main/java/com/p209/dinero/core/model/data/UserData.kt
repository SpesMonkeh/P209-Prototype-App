package com.p209.dinero.core.model.data

data class UserData(
	val userName: String?, // TODO Bør måske opbevares andetsteds og skal den være nullable?
	val useDynamicColor: Boolean,
	val showOnboarding: Boolean,
	val darkThemeConfig: DarkThemeConfig,
	val themeBrand: ThemeBrand
)