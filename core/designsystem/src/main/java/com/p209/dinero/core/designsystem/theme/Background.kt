package com.p209.dinero.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * **Now in Android dokumentation:**
 *```
 * A class to model background color and tonal elevation values for Now in Android.
 *
 * `
 */
@Immutable
data class BackgroundTheme(
	val color: Color = Color.Unspecified,
	val tonalElevation: Dp = Dp.Unspecified
)

/**
 * **Now in Android dokumentation:**
 *```
 * A composition local for [BackgroundTheme].
 *
 * `
 */
val LocalBackgroundTheme: ProvidableCompositionLocal<BackgroundTheme> = staticCompositionLocalOf { BackgroundTheme() }