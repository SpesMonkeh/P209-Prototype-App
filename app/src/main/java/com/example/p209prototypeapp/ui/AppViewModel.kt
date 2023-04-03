package com.example.p209prototypeapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AppViewModel : ViewModel() {
	private val uiState = MutableStateFlow(AppUiState())
}