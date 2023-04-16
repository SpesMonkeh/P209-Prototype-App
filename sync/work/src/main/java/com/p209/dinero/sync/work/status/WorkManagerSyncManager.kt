package com.p209.dinero.sync.work.status

import android.content.Context
import androidx.lifecycle.map
import androidx.lifecycle.asFlow
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkInfo.State
import androidx.work.WorkManager
import com.p209.dinero.core.data.util.SyncManager
import com.p209.dinero.sync.work.initializers.SyncWorkName
import com.p209.dinero.sync.work.workers.SyncWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

/** *Now in Android dokumentation:*
 *
 * [SyncManager] backed by [WorkInfo] from [WorkManager]
 *
 * `
 */
class WorkManagerSyncManager @Inject constructor(
	@ApplicationContext private val context: Context
) : SyncManager {
	override val isSyncing: Flow<Boolean> =
		WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData(SyncWorkName)
			.map(MutableList<WorkInfo>::anyRunning)
			.asFlow()
			.conflate()

	override fun requestSync() {
		val workManager = WorkManager.getInstance(context)
		/* Run sync on app startup and ensure only one sync worker runs at any time */
		workManager.enqueueUniqueWork(
			SyncWorkName,
			ExistingWorkPolicy.KEEP,
			SyncWorker.startUpSyncWork()
		)
	}
}

private val List<WorkInfo>.anyRunning get() = any { it.state == State.RUNNING }