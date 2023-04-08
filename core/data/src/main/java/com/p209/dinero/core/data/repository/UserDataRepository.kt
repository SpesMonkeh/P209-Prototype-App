package com.p209.dinero.core.data.repository

import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

	/**
	 * [UserData] strømmen.
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