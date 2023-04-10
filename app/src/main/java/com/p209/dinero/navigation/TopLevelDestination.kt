package com.p209.dinero.navigation

import com.p209.dinero.core.designsystem.icon.DineroIcons
import com.p209.dinero.core.designsystem.icon.Icon
import com.p209.dinero.core.designsystem.icon.Icon.DrawableResourceIcon
import com.p209.dinero.R.string as stringR
import com.p209.dinero.feature.budget.R as budgetR
import com.p209.dinero.feature.home.R as homeR
import com.p209.dinero.feature.pantry.R as pantryR

enum class TopLevelDestination(
	val selectedIcon: Icon,
	val unselectedIcon: Icon,
	val iconTextID: Int,
	val titleTextId: Int
) {
	HOME_TOP(
		selectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		unselectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		iconTextID = homeR.string.home,
		titleTextId = stringR.app_name // TODO Ændr til brugernavn og måske tidsbaseret hilsen
	),
	PANTRY_TOP(
		selectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		unselectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		iconTextID = pantryR.string.pantry,
		titleTextId = pantryR.string.pantry
	),
	BUDGET_TOP(
		selectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		unselectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		iconTextID = budgetR.string.budget,
		titleTextId = budgetR.string.budget
	)
}