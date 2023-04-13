package com.p209.dinero.core.datastore.preferences

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
	val language: Language = Language.English
)

enum class Language {
	Danish,
	English
}