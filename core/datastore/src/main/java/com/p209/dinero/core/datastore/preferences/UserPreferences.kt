package com.p209.dinero.core.datastore.preferences

import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
	val userName: String = "",
	val useDynamicColor: Boolean = true,
	val hideOnboarding: Boolean = false,
	val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
	val themeBrand: ThemeBrand = ThemeBrand.DEFAULT
)
