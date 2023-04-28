package com.p209.dinero.core.data.repository

import com.p209.dinero.core.datastore.AppSettingsDataSource
import com.p209.dinero.core.model.data.AppData
import com.p209.dinero.core.model.data.AppLanguage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstAppDataRepository @Inject constructor(
	private val appSettingsDataSource: AppSettingsDataSource,
) : AppDataRepository {

	override val appData: Flow<AppData> = appSettingsDataSource.appData

	override suspend fun setAppLanguage(language: AppLanguage) {
		appSettingsDataSource.setAppLanguage(language)
	}
}