package com.p209.dinero.core.data.repository

import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

	/**
	 * Værdi, som indeholder en reference til brugerens [UserData] såfremt, en bruger er blevet oprettet i app'en.
	 */
	val userData: Flow<UserData>

	/**
	 * Vælg, om brugeren har gennemført onboarding-processen.
	 */
	suspend fun setShowOnboarding(showOnboarding: Boolean)

	/**
	 * Sæt brugerens valgte brugernavn.
	 */
	suspend fun setUserName(userName: String)
}