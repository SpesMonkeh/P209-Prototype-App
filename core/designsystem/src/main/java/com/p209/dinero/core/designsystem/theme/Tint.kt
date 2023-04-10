package com.p209.dinero.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * **Now in Android dokumentation:**
 *
 * A class to model background color and tonal elevation values for Now in Android.
 *
 * `
 */
@Immutable
data class TintTheme(
	val iconTint: Color? = null
)

/**
 * **Now in Android dokumentation:**
 *
 * A composition local for [TintTheme].
 *
 * `
 */
val LocalTintTheme: ProvidableCompositionLocal<TintTheme> = staticCompositionLocalOf { TintTheme() }