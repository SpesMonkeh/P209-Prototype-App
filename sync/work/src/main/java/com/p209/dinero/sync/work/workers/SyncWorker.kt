package com.p209.dinero.sync.work.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.p209.dinero.core.common.network.di.DineroDispatchers.IO
import com.p209.dinero.core.common.network.di.Dispatcher
import com.p209.dinero.core.data.Synchronizer
import com.p209.dinero.core.datastore.DineroPreferencesDataSource
import com.p209.dinero.sync.work.initializers.SyncConstraints
import com.p209.dinero.sync.work.initializers.syncForegroundInfo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/** **Now in Android dokumentation:**
 *
 *  Syncs the data layer by delegating to the appropriate repository instances with sync functionality
 *
 *  `
 */
@HiltWorker
class SyncWorker @AssistedInject constructor(
	@Assisted private val appContext: Context,
	@Assisted workerParams: WorkerParameters,
	private val dineroPreferences: DineroPreferencesDataSource,
	@Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : CoroutineWorker(appContext, workerParams), Synchronizer {

	override suspend fun getForegroundInfo(): ForegroundInfo = appContext.syncForegroundInfo()

	override suspend fun doWork(): Result = withContext(ioDispatcher) {

		// First sync the repositories in parallel
		val syncedSuccessfully = true /*awaitAll().all { it }*/ // TODO Fjern true når app'en kan starte og vise UI

		if (syncedSuccessfully) {
			Result.success()
		} else {
			Result.retry()
		}
	}
	//override suspend fun getChangeListVersions(): ChangeListVersions =
	//	dineroPreferences.getChangeListVersions()

	//override suspend fun updateChangeListVersions(
	//	update: ChangeListVersions.() -> ChangeListVersions,
	//) = dineroPreferences.updateChangeListVersion(update)

	companion object {
		/**
		 * Expedited one time work to sync data on app startup
		 */
		fun startUpSyncWork() = OneTimeWorkRequestBuilder<DelegatingWorker>()
			.setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
			.setConstraints(SyncConstraints)
			.setInputData(SyncWorker::class.delegatedData())
			.build()
	}
}