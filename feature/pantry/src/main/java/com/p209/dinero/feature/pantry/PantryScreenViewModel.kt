package com.p209.dinero.feature.pantry

import androidx.lifecycle.ViewModel
import com.p209.dinero.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PantryScreenViewModel @Inject constructor(
	val userDataRepository: UserDataRepository
): ViewModel() {
}