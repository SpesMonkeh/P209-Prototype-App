package com.p209.dinero.sync.work.initializers

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.WorkManagerInitializer
import com.p209.dinero.sync.work.workers.SyncWorker

object Sync {
	/* Now in Android kommentar:
	   This method is a workaround to manually initialize the sync process instead of relying on automatic
	   initialization with Androidx Startup. It is called from the app module's Application.onCreate()
	   and should be done only once.*/
	fun initialize(context: Context) {
		AppInitializer.getInstance(context)
			.initializeComponent(SyncInitializer::class.java)
	}
}

/* This name should not be changed otherwise the app may have concurrent sync requests running */
internal const val SyncWorkName = "SyncWorkName"

/** Now in Android dokumentation:
 *
 * Registers work to sync the data layer periodically on app startup.
 *
 * `
 */
class SyncInitializer : Initializer<Sync> {
	override fun create(context: Context): Sync {
		WorkManager.getInstance(context).apply {			/* Run sync on app startup and ensure only one sync worker runs at any time. */
			enqueueUniqueWork(
				SyncWorkName,
				ExistingWorkPolicy.KEEP,
				SyncWorker.startUpSyncWork()
			)
			return Sync
		}
	}

	override fun dependencies(): List<Class<out Initializer<*>>> =
		listOf(WorkManagerInitializer::class.java)
}