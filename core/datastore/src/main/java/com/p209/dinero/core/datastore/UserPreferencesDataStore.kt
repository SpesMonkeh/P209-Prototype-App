package com.p209.dinero.core.datastore

import androidx.datastore.core.DataStore
import com.p209.dinero.core.datastore.preferences.UserPreferences
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataStore @Inject constructor(
	private val userPreferences: DataStore<UserPreferences>
) {
	val userData = userPreferences.data
		.map {
			UserData(
				username = it.userName,
				darkThemeConfig = it.darkThemeConfig,
				useDynamicColor = it.useDynamicColor,
				onboardingCompleted = it.onboardingCompleted,
				themeBrand = it.themeBrand
			)
		}

	suspend fun setUserName(userName: String) {
		userPreferences.updateData {
			it.copy(userName = userName)
		}
	}

	suspend fun saveOnboardingState(completed: Boolean) {
		userPreferences.updateData {
			it.copy(onboardingCompleted = completed)
		}
	}

	suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
		userPreferences.updateData {
			it.copy(useDynamicColor = useDynamicColor)
		}
	}

	suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
		userPreferences.updateData {
			it.copy(darkThemeConfig = darkThemeConfig)
		}
	}

	suspend fun setThemeBrand(themeBrand: ThemeBrand) {
		userPreferences.updateData {
			it.copy(themeBrand = themeBrand)
		}
	}
}