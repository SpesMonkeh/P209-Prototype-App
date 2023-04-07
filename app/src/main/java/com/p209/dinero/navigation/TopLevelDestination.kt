package com.p209.dinero.navigation

import androidx.annotation.StringRes
import com.p209.dinero.R.string as stringR
import com.p209.dinero.feature.pantry.R as pantryR
import com.p209.dinero.feature.budget.R as budgetR

enum class TopLevelDestination(
	@StringRes val titleTextId: Int
) {
	HOME(
		titleTextId = stringR.app_name // TODO Ændr til brugernavn og måske tidsbaseret hilsen
	),
	PANTRY(
		titleTextId = pantryR.string.pantry
	),
	Budget(
		titleTextId = budgetR.string.budget
	)
}