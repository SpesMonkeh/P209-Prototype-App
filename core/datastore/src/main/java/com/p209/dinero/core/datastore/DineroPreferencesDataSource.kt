package com.p209.dinero.core.datastore

import androidx.datastore.core.DataStore
import com.p209.dinero.core.datastore.preferences.UserPreferences
import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DineroPreferencesDataSource @Inject constructor(
	private val userPreferences: DataStore<UserPreferences>
) {
	val userData = userPreferences.data
		.map {
			UserData(
				userName = it.userName,
				darkThemeConfig = it.darkThemeConfig,
				useDynamicColor = it.useDynamicColor,
				hideOnboarding = it.hideOnboarding,
				themeBrand = it.themeBrand
			)
		}

	suspend fun setUserName(userName: String) {
		userPreferences.updateData {
			it.copy(
				userName = userName
			)
		}
	}

	suspend fun setHideOnboarding(hideOnboarding: Boolean) {
		userPreferences.updateData {
			it.copy(
				hideOnboarding = hideOnboarding
			)
		}
	}

	suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
		userPreferences.updateData {
			it.copy(
				useDynamicColor = useDynamicColor
			)
		}
	}
}