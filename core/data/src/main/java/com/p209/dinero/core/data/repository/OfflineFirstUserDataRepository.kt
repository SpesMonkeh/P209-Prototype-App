package com.p209.dinero.core.data.repository

import com.p209.dinero.core.datastore.UserPreferencesDataSource
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
	private val userPreferencesDataSource: UserPreferencesDataSource
) : UserDataRepository {

	override val userData: Flow<UserData> = userPreferencesDataSource.userData
	override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
		userPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
	}

	override suspend fun setUsername(userName: String) {
		userPreferencesDataSource.setUserName(userName)
	}

	override suspend fun setHideOnboarding(hideOnboarding: Boolean) {
		userPreferencesDataSource.setHideOnboarding(hideOnboarding)
	}

	override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
		userPreferencesDataSource.setThemeBrand(themeBrand)
	}

	override suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
		userPreferencesDataSource.setUseDynamicColor(useDynamicColor)
	}
}