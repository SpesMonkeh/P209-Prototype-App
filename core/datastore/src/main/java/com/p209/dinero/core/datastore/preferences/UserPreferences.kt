package com.p209.dinero.core.datastore.preferences

import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import kotlinx.serialization.Serializable

/** *SERIALIZABLE DATA CLASS*
 *
 * Contains the basic values, the user can modify, i.e. username, theme, eating-preferences etc..
 */
@Serializable
data class UserPreferences(
    val userName: String = "",
    val useDynamicColor: Boolean = true,
    val onboardingCompleted: Boolean = false,
    val darkThemeConfig: DarkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
    val themeBrand: ThemeBrand = ThemeBrand.DEFAULT
)

