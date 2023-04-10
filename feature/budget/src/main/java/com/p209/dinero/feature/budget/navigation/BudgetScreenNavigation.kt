package com.p209.dinero.feature.budget.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.p209.dinero.feature.budget.BudgetScreenRoute

const val BUDGET_SCREEN_NAVIGATION_ROUTE = "budget_screen_route"

fun NavController.navigateToBudget(navOptions: NavOptions? = null) {
	this.navigate(BUDGET_SCREEN_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.budgetScreen() {
	composable(route = BUDGET_SCREEN_NAVIGATION_ROUTE) {
		BudgetScreenRoute()
	}
}