package com.p209.dinero

import android.app.Application
import com.p209.dinero.sync.work.initializers.Sync
import dagger.hilt.android.HiltAndroidApp

/**
 * Programmets opstarts-funktion
 */
@HiltAndroidApp
class DineroApplication: Application()/*, ImageLoaderFactory*/ {

	// @Inject
	// lateinit var imageloader: Provider<ImageLoader>

	override fun onCreate() {
		super.onCreate()

		/* Now in Android kommentar: Initialize Sync; the system responsible for keeping data up to date. */
		Sync.initialize(context = this)
	}

	//override fun newImageLoader(): ImageLoader = imageLoader.get()
}