package com.p209.dinero.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * **Fra Now in Android:**
 *
 * A class to model background color and tonal elevation values for Now in Android.
 */
@Immutable
data class TintTheme(
	val iconTint: Color? = null
)

/**
 * **Fra Now in Android:**
 *```
 * A composition local for [TintTheme].
 */
val LocalTintTheme: ProvidableCompositionLocal<TintTheme> = staticCompositionLocalOf { TintTheme() }