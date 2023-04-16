package com.p209.dinero.core.data.repository

import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
/** *Interface*
 *
 * * Indeholder [Flow]<[userData]>
 * * Definerer funktioner, som tillader brugeren at ændre i de userData gemte værdier via app'ens indstillinger.
 *
 * `
 */
interface UserDataRepository {

	/**
	 * Værdi, som indeholder en reference til brugerens [UserData] såfremt, en bruger er blevet oprettet i app'en.
	 *
	 * `
	 */
	val userData: Flow<UserData>

	/**
	 * Vælg, om app'ens tema skal være [DarkThemeConfig.LIGHT], [DarkThemeConfig.DARK]
	 * eller benytte systemets indstilling via [DarkThemeConfig.FOLLOW_SYSTEM]
	 *
	 * `
	 */
	suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

	/**
	 * Vælg, om brugeren har gennemført onboarding-processen.
	 *
	 * `
	 */
	suspend fun setHideOnboarding(hideOnboarding: Boolean)

	/** Vælg, om app'ens farvetema skal benytte [ThemeBrand.DEFAULT] eller [ThemeBrand.ANDROID]
	 *
	 * `
	 */
	suspend fun setThemeBrand(themeBrand: ThemeBrand)

	/**
	 * Vælg, om app'ens tema skal benytte sig af Dynamic Color.
	 *
	 * `
	 */
	suspend fun setUseDynamicColor(useDynamicColor: Boolean)

	/**
	 * Sæt brugerens valgte brugernavn.
	 *
	 * `
	 */
	suspend fun setUsername(userName: String)
}