package com.p209.dinero.core.data.repository

import com.p209.dinero.core.model.data.AppData
import com.p209.dinero.core.model.data.AppLanguage
import kotlinx.coroutines.flow.Flow

/** *INTERFACE*
 *
 * Contains [Flow]<[appData]>.
 *
 * Defines functions, which allows the user to
 * change the saved values in appData via the app's settings.
 */
interface AppDataRepository {

	val appData: Flow<AppData>

	/** Set the language to be displayed throughout the app. */
	suspend fun setAppLanguage(language: AppLanguage)
}