package com.p209.dinero.sync.work.di

import com.p209.dinero.core.data.util.SyncManager
import com.p209.dinero.sync.work.status.WorkManagerSyncManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SyncModule {
	@Binds
	fun bindsSyncStatusMonitor(
		syncStatusMonitor: WorkManagerSyncManager
	) : SyncManager
}