package com.p209.dinero.core.datastore

import androidx.datastore.core.Serializer
import com.p209.dinero.core.datastore.preferences.UserPreferences
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/** En [Serializer] af værdier fra [UserPreferences].
 *
 * `
 */
@Suppress("BlockingMethodInNonBlockingContext")
class UserPreferencesSerializer @Inject constructor() : Serializer<UserPreferences> {
	override val defaultValue: UserPreferences
		get() = UserPreferences()

	override suspend fun readFrom(input: InputStream): UserPreferences {
		return try {
			Json.decodeFromString(
				deserializer = UserPreferences.serializer(),
				string = input.readBytes().decodeToString()
			)
		} catch (serializationException: SerializationException) {
			serializationException.printStackTrace()
			defaultValue
		}
	}

	override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
		output.write(
			Json.encodeToString(
				serializer = UserPreferences.serializer(),
				value = t
			).encodeToByteArray()
		)
	}
}