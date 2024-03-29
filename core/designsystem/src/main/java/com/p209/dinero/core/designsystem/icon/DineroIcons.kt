package com.p209.dinero.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.p209.dinero.core.designsystem.R

/**
 * Dinero ikoner. Material ikoner er af typen [ImageVector]. Egne ikoner er drawable resource ID'er.
 */
object DineroIcons {
	val TEST_chefs_hat = R.drawable.chefs_hat_stroke_24dp

}

object DineoIconOFV {
	@DrawableRes val house_stroke12: Int = R.drawable.house_stroke12_ofv
	@DrawableRes val cog_stroke12: Int = R.drawable.cog_stroke12_ofv
}

/**
 * *sealed class*
 *
 * Gør det nemmere at arbejde med [ImageVector] og [DrawableRes] ikoner.
 *
 * `
 */
sealed class Icon {
	data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
	data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}