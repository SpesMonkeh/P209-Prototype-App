package com.p209.dinero.core.data.di

import com.p209.dinero.core.data.repository.OfflineFirstUserDataRepository
import com.p209.dinero.core.data.repository.UserDataRepository
import com.p209.dinero.core.data.util.ConnectivityManagerNetworkMonitor
import com.p209.dinero.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

	@Binds
	fun bindsUserDataRepository(
		userDataRepository: OfflineFirstUserDataRepository
	): UserDataRepository

	@Binds
	fun bindsNetworkMonitor(
		networkMonitor: ConnectivityManagerNetworkMonitor,
	): NetworkMonitor
}