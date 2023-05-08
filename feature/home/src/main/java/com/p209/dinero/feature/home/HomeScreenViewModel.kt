package com.p209.dinero.feature.home
import androidx.lifecycle.ViewModel
import com.p209.dinero.core.data.repository.AppDataRepository
import com.p209.dinero.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
	private val userDataRepository: UserDataRepository,
	private val appDataRepository: AppDataRepository,
) : ViewModel() {

}