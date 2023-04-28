package com.p209.dinero.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.core.data.repository.AppDataRepository
import com.p209.dinero.core.model.data.AppLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppSettingsViewModel @Inject constructor(
	private val appDataRepo: AppDataRepository
) : ViewModel() {
	val appSettingsSettingsUiState: StateFlow<AppSettingsSettingsUiState> =
		appDataRepo.appData
			.map { appData ->
				AppSettingsSettingsUiState.Success(
					appSettings = EditableAppSettings(
						appLanguage = appData.language,
					)
				)
			}
			.stateIn(
				scope = viewModelScope,
				// Now in Android kommentar:
				// Starting eagerly means the user data is ready when the SettingsDialog is laid out
				// for the first time. Without this, due to b/221643630 the layout is done using the
				// "Loading" text, then replaced with the user editable fields once loaded, however,
				// the layout height doesn't change meaning all the fields are squashed into a small
				// scrollable column.
				// TODO: Change to SharingStarted.WhileSubscribed(5_000) when b/221643630 is fixed
				started = SharingStarted.Eagerly,
				initialValue = AppSettingsSettingsUiState.Loading,
			)

	fun updateAppLanguage(language: AppLanguage) {
		viewModelScope.launch {
			appDataRepo.setAppLanguage(language)
		}
	}
}

/** *DATA CLASS*
 *
 *  Contains those app settings, which it is possible for the user to adjust.
 *
 *  See [EditableUserPreferences] for settings related to the user's preferences
 *  rather than the settings of the app.
 */
data class EditableAppSettings(
	val appLanguage: AppLanguage,
)

sealed interface AppSettingsSettingsUiState {
	object Loading: AppSettingsSettingsUiState
	data class Success(val appSettings: EditableAppSettings): AppSettingsSettingsUiState
}