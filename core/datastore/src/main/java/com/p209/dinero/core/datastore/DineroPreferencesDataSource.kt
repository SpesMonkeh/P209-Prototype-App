package com.p209.dinero.core.datastore

import com.p209.dinero.core.model.data.UserData
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DineroPreferencesDataSource @Inject constructor(
	private val userPreferences: DataStore<UserPreferences>
) {
	val userData = userPreferences.data
		.map {
			UserData(
				userName = it.
			)
		}
}