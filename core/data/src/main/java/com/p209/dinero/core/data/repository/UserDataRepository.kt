package com.p209.dinero.core.data.repository

import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

	val userData: Flow<UserData>

	suspend fun setShowOnboarding(showOnboarding: Boolean)
}