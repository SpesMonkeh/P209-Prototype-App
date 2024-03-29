package com.p209.dinero.core.datastore

import androidx.datastore.core.Serializer
import com.p209.dinero.core.datastore.preferences.AppSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/** A [Serializer] of values from [AppSettings]. */
@Suppress("BlockingMethodInNonBlockingContext")
class AppSettingsSerializer @Inject constructor() : Serializer<AppSettings> {
	override val defaultValue: AppSettings
		get() = AppSettings()

	override suspend fun readFrom(input: InputStream): AppSettings {
		return try {
			Json.decodeFromString(
				deserializer = AppSettings.serializer(),
				string = input.readBytes().decodeToString()
			)
		} catch (serializationE: SerializationException) {
			serializationE.printStackTrace()
			defaultValue
		}
	}

	override suspend fun writeTo(t: AppSettings, output: OutputStream) {
		output.write(
			Json.encodeToString(
				serializer = AppSettings.serializer(),
				value = t
			).encodeToByteArray()
		)
	}
}