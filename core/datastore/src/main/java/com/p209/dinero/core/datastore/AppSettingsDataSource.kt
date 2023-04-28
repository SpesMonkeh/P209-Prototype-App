package com.p209.dinero.core.datastore

import androidx.datastore.core.DataStore
import com.p209.dinero.core.datastore.preferences.AppSettings
import com.p209.dinero.core.model.data.AppData
import com.p209.dinero.core.model.data.AppLanguage
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppSettingsDataSource @Inject constructor(
	private val appSettings: DataStore<AppSettings>
) {
	val appData = appSettings.data
		.map {
			AppData(
				language = it.language,
			)
		}

	suspend fun setAppLanguage(language: AppLanguage) {
		appSettings.updateData {
			it.copy(
				language = language,
			)
		}
	}
}