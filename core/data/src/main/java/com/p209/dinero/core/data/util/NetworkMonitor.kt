package com.p209.dinero.core.data.util

import kotlinx.coroutines.flow.Flow

/**
 * *Utility-funktion*
 *
 * Rapporterer programmets netværksstatus.
 *
 * `
 */
interface NetworkMonitor {
	val isOnline: Flow<Boolean>
}