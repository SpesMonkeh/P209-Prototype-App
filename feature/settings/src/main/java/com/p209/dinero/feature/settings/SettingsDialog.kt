package com.p209.dinero.feature.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.p209.dinero.core.designsystem.theme.DineroTheme
import com.p209.dinero.core.designsystem.theme.supportsDynamicTheming
import com.p209.dinero.core.model.data.DarkThemeConfig
import com.p209.dinero.core.model.data.ThemeBrand
import com.p209.dinero.feature.settings.R.string

@Composable
fun SettingsDialog(
	onDismiss: () -> Unit,
	versionName: String,
	viewModel: SettingsViewModel = hiltViewModel(),
) {
	val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()

	SettingsDialog(
		onDismiss = onDismiss,
		settingsUiState = settingsUiState,
		onChangeThemeBrand = viewModel::updateThemeBrand,
		onChangeDynamicColorPreference = viewModel::updateDynamicColorPreference,
		onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
		onChangeUsername = viewModel::updateUsername,
		versionName = versionName,
	)
}

@Composable
fun SettingsDialog(
	onDismiss: () -> Unit,
	settingsUiState: SettingsUiState,
	onChangeThemeBrand: (themeBrand:ThemeBrand) -> Unit,
	onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
	onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
	onChangeUsername: (username: String) -> Unit,
	versionName: String = "x.y.z",
	supportDynamicColor: Boolean = supportsDynamicTheming(),
) {
	val configuration = LocalConfiguration.current

	/** *Now in Android dokumentation:*
	 *
	 * `usePlatformDefaultWidth = false` is used as a temporary fix to allow height recalculation during recomposition.
	 * This, however, causes Dialog's to occupy full width in Compact mode. Therefore max width is configured below.
	 * This should be removed when there's a fix to
	 * https://issuetracker.google.com/issues/221643630
	 *
	 * `
	 */
	AlertDialog(
		properties = DialogProperties(usePlatformDefaultWidth = false),
		modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
		onDismissRequest = { onDismiss() },
		title = {
			Text(
				text = stringResource(string.settings_title),
				style = MaterialTheme.typography.titleLarge
			)
		},
		text = {
			Divider()
			Column(Modifier.verticalScroll(rememberScrollState())) {
				when (settingsUiState) {
					SettingsUiState.Loading -> {
						Text(
							text = stringResource(string.loading),
							modifier = Modifier.padding(vertical = 16.dp)
						)
					}

					is SettingsUiState.Success -> {
						SettingsPanel(
							settings = settingsUiState.settings,
							supportDynamicColor = supportDynamicColor,
							onChangeUsername = onChangeUsername,
							onChangeThemeBrand = onChangeThemeBrand,
							onChangeDynamicColorPreference = onChangeDynamicColorPreference,
							onChangeDarkThemeConfig = onChangeDarkThemeConfig
						)
					}
				}
				Divider(Modifier.padding(top = 8.dp))

				LinksPanel()

				Divider(Modifier.padding(top = 8.dp))

				AppVersionPanel(versionName)
			}
			// TrackScreenViewEvent(screenName = "Settings")
		},
		confirmButton = {
			Text(
				text = stringResource(string.ok),
				style = MaterialTheme.typography.labelLarge,
				color = MaterialTheme.colorScheme.primary,
				modifier = Modifier
					.padding(horizontal = 8.dp)
					.clickable { onDismiss() }
			)
		}
	)
}

@Composable
fun AppVersionPanel(versionName: String) {
	Row(
		modifier = Modifier.padding(top = 16.dp),
		horizontalArrangement = Arrangement.Center
	)  {
		Text(
			text = stringResource(string.app_version_name, versionName),
			style = MaterialTheme.typography.labelLarge,
			color = MaterialTheme.colorScheme.primary,
			modifier = Modifier
				.padding(vertical = 8.dp)
		)
	}
}

@Composable
private fun SettingsPanel(
	settings: UserEditableSettings,
	supportDynamicColor: Boolean,
	onChangeUsername: (username: String) -> Unit,
	onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
	onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
	onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit
) {
	val userCanChangeDynamicColor = settings.brand == ThemeBrand.DEFAULT
			&& supportDynamicColor

	SettingsDialogSectionTitle(text = stringResource(string.username))
	Column(Modifier.selectableGroup()) {
		OutlinedTextField(
			value = settings.username,
			onValueChange = { onChangeUsername(it) }
		)
	}

	ShowThemeBrandSetting(
		currentThemeBrand = settings.brand,
		onChangeThemeBrand = onChangeThemeBrand,
	)

	ShowUseDynamicColorSettingIfPossible(
		canChangeDynamicColor = userCanChangeDynamicColor,
		useDynamicColor = settings.useDynamicColor,
		onChangeDynamicColorPreference = onChangeDynamicColorPreference,
	)

	ShowDarkModePreferenceSetting(
		currentDarkThemeConfig = settings.darkThemeConfig,
		onChangeDarkThemeConfig = onChangeDarkThemeConfig,
	)
}

@Composable
private fun ShowThemeBrandSetting(
	currentThemeBrand: ThemeBrand,
	onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
) {
	SettingsDialogSectionTitle(stringResource(string.theme))

	Column(Modifier.selectableGroup()) {
		SettingsDialogThemeChooserRow(
			text = stringResource(string.brand_default),
			selected = currentThemeBrand == ThemeBrand.DEFAULT,
			onClick = { onChangeThemeBrand(ThemeBrand.DEFAULT) }
		)
		SettingsDialogThemeChooserRow(
			text = stringResource(string.brand_android),
			selected = currentThemeBrand  == ThemeBrand.ANDROID,
			onClick = { onChangeThemeBrand(ThemeBrand.ANDROID) }
		)
	}
}

@Composable
private fun ShowUseDynamicColorSettingIfPossible(
	canChangeDynamicColor: Boolean,
	useDynamicColor: Boolean,
	onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
) {
	if (!canChangeDynamicColor) return

	SettingsDialogSectionTitle(stringResource(string.use_dynamic_color))

	Column(Modifier.selectableGroup()) {
		SettingsDialogThemeChooserRow(
			text = stringResource(string.yes),
			selected = useDynamicColor,
			onClick = { onChangeDynamicColorPreference(true) }
		)
		SettingsDialogThemeChooserRow(
			text = stringResource(string.no),
			selected = useDynamicColor,
			onClick = { onChangeDynamicColorPreference(false) }
		)
	}
}

@Composable
fun ShowDarkModePreferenceSetting(
	currentDarkThemeConfig: DarkThemeConfig,
	onChangeDarkThemeConfig: (DarkThemeConfig: DarkThemeConfig) -> Unit,
) {
	SettingsDialogSectionTitle(stringResource(string.dark_mode_preference))

	Column(Modifier.selectableGroup()) {
		SettingsDialogThemeChooserRow(
			text = stringResource(string.dark_mode_config_system),
			selected = currentDarkThemeConfig == DarkThemeConfig.FOLLOW_SYSTEM,
			onClick = { onChangeDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM) }
		)
		SettingsDialogThemeChooserRow(
			text = stringResource(string.dark_mode_config_light),
			selected = currentDarkThemeConfig == DarkThemeConfig.LIGHT,
			onClick = { onChangeDarkThemeConfig(DarkThemeConfig.LIGHT) }
		)
		SettingsDialogThemeChooserRow(
			text = stringResource(string.dark_mode_config_dark),
			selected = currentDarkThemeConfig == DarkThemeConfig.DARK,
			onClick = { onChangeDarkThemeConfig(DarkThemeConfig.DARK) }
		)
	}
}

@Composable
private fun SettingsDialogSectionTitle(text: String) {
	Text(
		text = text,
		style = MaterialTheme.typography.titleMedium,
		modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
	)
}

@Composable
private fun SettingsDialogThemeChooserRow(
	text: String,
	selected: Boolean,
	onClick: () -> Unit
) {
	Row(
		Modifier
			.fillMaxWidth()
			.selectable(
				selected = selected,
				role = Role.RadioButton,
				onClick = onClick
			)
			.padding(12.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		RadioButton(
			selected = selected,
			onClick = null
		)
		Spacer(Modifier.width(8.dp))
		Text(text)
	}
}

@Composable
private fun LinksPanel() {
	Row(
		modifier = Modifier.padding(top = 16.dp),
	) {
		Column(
			Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Row {
				TextLink( // TODO Fjern disse TextLinks hvis ikke vi skal have nogle links.
					text = "Google Link", // Kun til test
					url = "https://google.com" // Kun til test
				)
				Spacer(Modifier.width(16.dp))
				TextLink(
					text = "AAU Link", // Kun til test
					url = "https://aau.dk" // Kun til test
				)
			}
			Row {
				TextLink(
					text = "Moodle Link", // Kun til test
					url = "https://moodle.aau.dk/my/" // Kun til test
				)
			}
		}
	}
}

@Composable
private fun TextLink(text: String, url: String) {
	val launchResourceIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
	val context = LocalContext.current

	Text(
		text = text,
		style = MaterialTheme.typography.labelLarge,
		color = MaterialTheme.colorScheme.primary,
		modifier = Modifier
			.padding(vertical = 8.dp)
			.clickable {
				ContextCompat.startActivity(
					context,
					launchResourceIntent,
					null
				)
			},
	)
}

@Preview
@Composable
private fun PreviewSettingsDialog() {
	DineroTheme {
		SettingsDialog(
			onDismiss = {},
			settingsUiState = SettingsUiState.Success(
				UserEditableSettings(
					brand =  ThemeBrand.DEFAULT,
					darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
					useDynamicColor = false,
					username = "Johnny Bib"
				),
			),
			onChangeThemeBrand = {},
			onChangeDynamicColorPreference = {},
			onChangeDarkThemeConfig = {},
			onChangeUsername = {},
		)
	}
}

@Preview
@Composable
private fun PreviewSettingsDialogLoading() {
	DineroTheme {
		SettingsDialog(
			onDismiss = {},
			settingsUiState = SettingsUiState.Loading,
			onChangeThemeBrand = {},
			onChangeDynamicColorPreference = {},
			onChangeDarkThemeConfig = {},
			onChangeUsername = {},
		)
	}
}