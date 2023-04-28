package com.p209.dinero.core.datastore.preferences

import com.p209.dinero.core.model.data.AppLanguage
import kotlinx.serialization.Serializable

/** *SERIALIZABLE DATA CLASS*
 *
 *  Contains the serializable data values, which affect the app, but are not considered
 *  to be part of the user's personal preferences.
 *  See [UserPreferences] for the values relating to the user's personal preferences.
 */
@Serializable
data class AppSettings(
	val language: AppLanguage = AppLanguage.English
)