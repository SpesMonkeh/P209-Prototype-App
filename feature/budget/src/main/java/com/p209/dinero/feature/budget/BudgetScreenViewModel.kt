package com.p209.dinero.feature.budget

import androidx.lifecycle.ViewModel
import com.p209.dinero.core.data.repository.UserDataRepository
import javax.inject.Inject

class BudgetScreenViewModel @Inject constructor(
	val userDataRepo: UserDataRepository
) : ViewModel() {

}