package com.p209.dinero.navigation

import androidx.annotation.StringRes
import com.p209.dinero.R.string as stringR
import com.p209.dinero.feature.budget.R as budgetR
import com.p209.dinero.feature.pantry.R as pantryR

enum class TopLevelDestination(
	@StringRes val titleTextId: Int
) {
	HOME_TOP(
		titleTextId = stringR.app_name // TODO Ændr til brugernavn og måske tidsbaseret hilsen
	),
	PANTRY_TOP(
		titleTextId = pantryR.string.pantry
	),
	BUDGET_TOP(
		titleTextId = budgetR.string.budget
	)
}