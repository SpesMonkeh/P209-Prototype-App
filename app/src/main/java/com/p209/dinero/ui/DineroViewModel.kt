package com.p209.dinero.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DineroViewModel : ViewModel() {
	private val uiState = MutableStateFlow(DineroUiState())
	val UiState: StateFlow<DineroUiState> = uiState.asStateFlow()

}