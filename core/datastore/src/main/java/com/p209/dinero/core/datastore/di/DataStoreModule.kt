package com.p209.dinero.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.p209.dinero.core.common.network.di.DineroDispatchers.IO
import com.p209.dinero.core.common.network.di.Dispatcher
import com.p209.dinero.core.datastore.AppSettingsSerializer
import com.p209.dinero.core.datastore.UserPreferencesSerializer
import com.p209.dinero.core.datastore.preferences.AppSettings
import com.p209.dinero.core.datastore.preferences.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
	@Provides
	@Singleton
	fun providesUserPreferencesDataStore(
		@ApplicationContext context: Context,
		@Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
		userPreferencesSerializer: UserPreferencesSerializer,
	) : DataStore<UserPreferences> =
		DataStoreFactory.create(
			serializer = userPreferencesSerializer,
			scope = CoroutineScope(ioDispatcher + SupervisorJob()),
		) {
			context.dataStoreFile("user-preferences.json")
		}

	@Provides
	@Singleton
	fun providesAppSettingsDataStore(
		@ApplicationContext context: Context,
		@Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
		appSettingsSerializer: AppSettingsSerializer,
	) : DataStore<AppSettings> =
		DataStoreFactory.create(
			serializer = appSettingsSerializer,
			scope = CoroutineScope(ioDispatcher + SupervisorJob()),
		) {
			context.dataStoreFile("app-settings.json")
		}
}