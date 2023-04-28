package com.p209.dinero.feature.home
import androidx.lifecycle.ViewModel
import com.p209.dinero.core.data.repository.AppDataRepository
import com.p209.dinero.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
	private val userDataRepository: UserDataRepository,
	private val appDataRepository: AppDataRepository,
) : ViewModel() {

	private val showOnboarding: Flow<Boolean> = userDataRepository.userData.map { !it.hideOnboarding }
}