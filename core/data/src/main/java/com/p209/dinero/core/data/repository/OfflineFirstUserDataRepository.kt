package com.p209.dinero.core.data.repository

import com.p209.dinero.core.datastore.DineroPreferencesDataSource
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
	private val dineroPreferencesDataSource: DineroPreferencesDataSource
) : UserDataRepository {

	override val userData: Flow<UserData> = dineroPreferencesDataSource.userData
	override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
		dineroPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
	}

	override suspend fun setUsername(userName: String) {
		dineroPreferencesDataSource.setUserName(userName)
	}

	override suspend fun setHideOnboarding(hideOnboarding: Boolean) {
		dineroPreferencesDataSource.setHideOnboarding(hideOnboarding)
	}

	override suspend fun setThemeBrand(themeBrand: ThemeBrand) {
		dineroPreferencesDataSource.setThemeBrand(themeBrand)
	}

	override suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
		dineroPreferencesDataSource.setUseDynamicColor(useDynamicColor)
	}
}