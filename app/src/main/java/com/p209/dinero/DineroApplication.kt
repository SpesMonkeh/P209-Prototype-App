package com.p209.dinero

import android.app.Application

/**
 * Programmets opstarts-funktion
 */
class DineroApplication: Application() {
	override fun onCreate() {
		super.onCreate()

		/* Kommentar fra Now in Android:
		 * Initialize Sync; the system responsible for keeping data up to date.
		 */
		// TODO Indsæt, hvis det bliver nødvendigt. Ellers; fjern => Sync.initialize(context = this)
	}
}