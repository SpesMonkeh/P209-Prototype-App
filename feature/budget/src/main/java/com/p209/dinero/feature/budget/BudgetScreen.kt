package com.p209.dinero.feature.budget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun BudgetScreenRoute(
	modifier: Modifier = Modifier,
	viewModel: BudgetScreenViewModel = hiltViewModel()
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