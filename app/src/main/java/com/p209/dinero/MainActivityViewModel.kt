package com.p209.dinero

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.MainActivityUiState.Loading
import com.p209.dinero.MainActivityUiState.Success
import com.p209.dinero.core.data.repository.AppDataRepository
import com.p209.dinero.core.data.repository.UserDataRepository
import com.p209.dinero.core.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
	userDataRepository: UserDataRepository,
	appDataRepository: AppDataRepository,
) : ViewModel() {
	val timeOut: Long = 5_000
	val uiState: StateFlow<MainActivityUiState> = userDataRepository.userData.map {
		Success(it)
	}.stateIn(
		scope = viewModelScope,
		initialValue = Loading,
		started = SharingStarted.WhileSubscribed(stopTimeoutMillis = timeOut)
	)
}

sealed interface MainActivityUiState {
	object Loading: MainActivityUiState
	data class Success(val userData: UserData): MainActivityUiState
}