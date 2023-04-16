package com.p209.dinero.core.data.util

import kotlinx.coroutines.flow.Flow

interface SyncManager {
	val isSyncing: Flow<Boolean>

	fun requestSync()
}