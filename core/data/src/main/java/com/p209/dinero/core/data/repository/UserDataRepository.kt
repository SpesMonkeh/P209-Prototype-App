package com.p209.dinero.core.data.repository

import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
/** *Interface*
 *
 * * Indeholder [Flow]<[userData]>
 * * Definerer funktioner, som tillader brugeren at ændre i de userData gemte værdier via app'ens indstillinger.
 */
interface UserDataRepository {

	/**
	 * Værdi, som indeholder en reference til brugerens [UserData] såfremt, en bruger er blevet oprettet i app'en.
	 */
	val userData: Flow<UserData>

	/**
	 * Vælg, om app'ens tema skal være [DarkThemeConfig.LIGHT], [DarkThemeConfig.DARK]
	 * eller benytte systemets indstilling via [DarkThemeConfig.FOLLOW_SYSTEM]
	 */
	suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

	/** Set if the user has completed the onboarding-process. */
	suspend fun saveOnboardingState(completed: Boolean)

	/** Select, if the app color theme should use [ThemeBrand.DEFAULT] or [ThemeBrand.ANDROID]. */
	suspend fun setThemeBrand(themeBrand: ThemeBrand)

	/** Select, if the app theme should utilize Dynamic Color.*/
	suspend fun setUseDynamicColor(useDynamicColor: Boolean)

	/** Set the user's chosen username. */
	suspend fun setUsername(userName: String)
}