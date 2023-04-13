package com.p209.dinero.feature.budget

import androidx.lifecycle.ViewModel
import com.p209.dinero.core.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BudgetScreenViewModel @Inject constructor(
	val userDataRepo: UserDataRepository
) : ViewModel() {

}