package com.p209.dinero.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.core.data.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
	private val userDataRepo: UserDataRepository
) : ViewModel() {

	private val showOnboarding: Flow<Boolean> = userDataRepo.userData.map { it.showOnboarding }

	fun dismissOnboarding() {
		viewModelScope.launch {
			userDataRepo.setShowOnboarding(false)
		}
	}
}