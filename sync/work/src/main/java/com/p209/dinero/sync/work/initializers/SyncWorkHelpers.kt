package com.p209.dinero.sync.work.initializers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Constraints
import androidx.work.ForegroundInfo
import androidx.work.NetworkType

private const val SYNC_NOTIFICATION_ID = 0
private const val SYNC_NOTIFICATION_CHANNEL_ID = "SyncNotificationChannel"

/* Now in Android kommentar:
   All sync work needs an internet connection */
val SyncConstraints
	get() = Constraints.Builder()
		.setRequiredNetworkType(NetworkType.CONNECTED)
		.build()

/** *Now in Android dokumentation:*
 *
 * Foreground information for sync on lower API levels when sync workers are being
 * run with a foreground service
 *
 *  `
 */
fun Context.syncForegroundInfo() = ForegroundInfo(
	SYNC_NOTIFICATION_ID,
	syncWorkNotification()
)

/** *Now in Android dokumentation:*
 *
 * Notification displayed on lower API levels when sync workers are being
 * run with a foreground service
 *
 *  `
 */
private fun Context.syncWorkNotification(): Notification {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
		val channel = NotificationChannel(
			SYNC_NOTIFICATION_CHANNEL_ID,
			"Sync", // TODO Brug eventuelt getString()
			NotificationManager.IMPORTANCE_DEFAULT
		).apply {
			description = "Background tasks for Dinero" // TODO Brug eventuelt getString()
		}
		/* Now in Android kommentar:
		   Register the channel with the system */
		val notificationManager: NotificationManager? =
			getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

		notificationManager?.createNotificationChannel(channel)
	}

	return NotificationCompat.Builder(
		this,
		SYNC_NOTIFICATION_CHANNEL_ID
	)
		//.setSmallIcon( INDSÆT R.drawable. )
		.setContentTitle("Dinero")
		.setPriority(NotificationCompat.PRIORITY_DEFAULT)
		.build()
}