package com.p209.dinero.navigation

import com.p209.dinero.core.designsystem.icon.DineroIconOFV
import com.p209.dinero.core.designsystem.icon.DineroIcons
import com.p209.dinero.core.designsystem.icon.Icon
import com.p209.dinero.core.designsystem.icon.Icon.DrawableResourceIcon
import com.p209.dinero.R.string as stringR
import com.p209.dinero.feature.budget.R as budgetR
import com.p209.dinero.feature.home.R as homeR
import com.p209.dinero.feature.onboarding.R as onboardingR
import com.p209.dinero.feature.pantry.R as pantryR

enum class TopLevelDestination(
	val titleTextId: Int,
	val iconTextID: Int? = null,
	val selectedIcon: Icon? = null,
	val unselectedIcon: Icon? = null,
	val showTopAppBar: Boolean = true,
	val showNavRail: Boolean = true,
	val includeOnNavigationRail: Boolean = true,
) {
	HOME_TOP(
		titleTextId = stringR.app_name, // TODO Ændr til brugernavn og måske tidsbaseret hilsen
		iconTextID = homeR.string.home,
		selectedIcon = DrawableResourceIcon(DineroIconOFV.house_stroke12),
		unselectedIcon = DrawableResourceIcon(DineroIconOFV.house_stroke12)
	),
	PANTRY_TOP(
		titleTextId = pantryR.string.pantry,
		iconTextID = pantryR.string.pantry,
		selectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		unselectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		showNavRail = false
	),
	BUDGET_TOP(
		titleTextId = budgetR.string.budget,
		iconTextID = budgetR.string.budget,
		selectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
		unselectedIcon = DrawableResourceIcon(DineroIcons.TEST_chefs_hat),
	),
	ONBOARDING_TOP(
		titleTextId = onboardingR.string.onboarding,
		showTopAppBar = false,
		showNavRail = false,
		includeOnNavigationRail = false,
	)
}