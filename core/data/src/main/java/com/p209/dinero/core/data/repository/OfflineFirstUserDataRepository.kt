package com.p209.dinero.core.data.repository

import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
	private val dineroPreferencesDataSource: DineroPreferencesDataSource
) : UserDataRepository {

	override val userData: Flow<UserData> = dineroPreferencesDataSource.userData

}