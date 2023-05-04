package com.p209.dinero.core.data.repository

import com.p209.dinero.core.datastore.UserPreferencesDataStore
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
	private val userPreferencesDataStore: UserPreferencesDataStore
) : UserDataRepository {

	override val userData: Flow<UserData> = userPreferencesDataStore.userData
	override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
		userPreferencesDataStore.setDarkThemeConfig(darkThemeConfig)
	}

	override suspend fun setUsername(userName: String) {
		userPreferencesDataStore.setUserName(userName)
	}

	override suspend fun saveOnboardingState(completed: Boolean) {
		userPreferencesDataStore.saveOnboardingState(completed)
	}

	override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
		userPreferencesDataStore.setThemeBrand(themeBrand)
	}

	override suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
		userPreferencesDataStore.setUseDynamicColor(useDynamicColor)
	}
}