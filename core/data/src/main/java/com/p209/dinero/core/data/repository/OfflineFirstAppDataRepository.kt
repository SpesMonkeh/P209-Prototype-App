package com.p209.dinero.core.data.repository

import com.p209.dinero.core.datastore.AppSettingsDataStore
import com.p209.dinero.core.model.data.AppData
import com.p209.dinero.core.model.data.AppLanguage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstAppDataRepository @Inject constructor(
	private val appSettingsDataStore: AppSettingsDataStore,
) : AppDataRepository {

	override val appData: Flow<AppData> = appSettingsDataStore.appData

	override suspend fun setAppLanguage(language: AppLanguage) {
		appSettingsDataStore.setAppLanguage(language)
	}
}