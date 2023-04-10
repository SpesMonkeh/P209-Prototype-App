package com.p209.dinero.feature.budget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun BudgetScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: BudgetScreenViewModel = viewModel()
) {
	BudgetScreen(
		modifier = modifier
	)
}

@Composable
fun BudgetScreen(
	modifier: Modifier = Modifier
) {

}