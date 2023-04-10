package com.p209.dinero.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * **Fra Now in Android:**
 *```
 * A class to model gradient color values for Now in Android.
 *
 * @param top The top gradient color to be rendered.
 * @param bottom The bottom gradient color to be rendered.
 * @param container The container gradient color over which the gradient will be rendered.
 */
@Immutable
data class GradientColors(
	val top: Color = Color.Unspecified,
	val bottom: Color = Color.Unspecified,
	val container: Color = Color.Unspecified
)

/**
 * **Fra Now in Android:**
 * ```
 * A composition local for [GradientColors]
 */
val LocalGradientColors: ProvidableCompositionLocal<GradientColors> = staticCompositionLocalOf { GradientColors() }