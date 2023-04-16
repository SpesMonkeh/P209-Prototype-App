package com.p209.dinero.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p209.dinero.core.data.repository.UserDataRepository
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
	private val userDataRepo: UserDataRepository
) : ViewModel() {
	val settingsUiState: StateFlow<SettingsUiState> =
		userDataRepo.userData
			.map { userData ->
				SettingsUiState.Success(
					settings = UserEditableSettings(
						brand = userData.themeBrand,
						useDynamicColor = userData.useDynamicColor,
						darkThemeConfig = userData.darkThemeConfig,
						username = userData.username ?: "[NOT PROVIDED]"
					),
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
				initialValue = SettingsUiState.Loading,
			)

	fun updateUsername(username: String) {
		viewModelScope.launch {
			userDataRepo.setUsername(username)
		}
	}

	fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
		viewModelScope.launch {
			userDataRepo.setDarkThemeConfig(darkThemeConfig)
		}
	}

	fun updateDynamicColorPreference(useDynamicColor: Boolean) {
		viewModelScope.launch {
			userDataRepo.setUseDynamicColor(useDynamicColor)
		}
	}

	fun updateThemeBrand(themeBrand: ThemeBrand) {
		viewModelScope.launch {
			userDataRepo.setThemeBrand(themeBrand)
		}
	}
}

/** *Data Class*
 *
 *  Indeholder de indstillinger, som det er muligt for brugeren at justere på.
 *
 *  `
 */
data class UserEditableSettings(
	val brand: ThemeBrand,
	val useDynamicColor: Boolean,
	val darkThemeConfig: DarkThemeConfig,
	val username: String,
)

sealed interface SettingsUiState {
	object Loading: SettingsUiState
	data class Success(val settings: UserEditableSettings): SettingsUiState
}