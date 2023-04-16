package com.p209.dinero.feature.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.p209.dinero.core.designsystem.theme.*
import com.p209.dinero.core.model.data.*

@Composable
fun SettingsDialog(
	onDismiss: () -> Unit,
	viewModel: SettingsViewModel = hiltViewModel(),
) {
	val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()

	SettingsDialog(
		onDismiss = onDismiss,
		settingsUiState = settingsUiState,
		onChangeThemeBrand = viewModel::updateThemeBrand,
		onChangeDynamicColorPreference = viewModel::updateDynamicColorPreference,
		onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDialog(
	settingsUiState: SettingsUiState,
	supportDynamicColor: Boolean = supportsDynamicTheming(),
	onDismiss: () -> Unit,
	onChangeThemeBrand: (themeBrand:ThemeBrand) -> Unit,
	onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
	onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
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
				text = "Settings", // TODO flyt til String Resources
				style = MaterialTheme.typography.titleLarge
			)
		},
		text = {
			Divider()
			Column(Modifier.verticalScroll(rememberScrollState())) {
				when (settingsUiState) {
					SettingsUiState.Loading -> {
						Text(
							text = "Loading...", // TODO flyt til String Resources
							modifier = Modifier.padding(vertical = 16.dp)
						)
					}

					is SettingsUiState.Success -> {
						SettingsPanel(
							settings = settingsUiState.settings,
							supportDynamicColor = supportDynamicColor,
							onChangeThemeBrand = onChangeThemeBrand,
							onChangeDynamicColorPreference = onChangeDynamicColorPreference,
							onChangeDarkThemeConfig = onChangeDarkThemeConfig
						)
					}
				}
				Divider(Modifier.padding(top = 8.dp))
				LinksPanel()
			}
			// TrackScreenViewEvent(screenName = "Settings")
		},
		confirmButton = {
			Text(
				text = "OK", // TODO flyt til String Resources
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
private fun SettingsPanel(
	settings: UserEditableSettings,
	supportDynamicColor: Boolean,
	onChangeThemeBrand: (themeBrand: ThemeBrand) -> Unit,
	onChangeDynamicColorPreference: (useDynamicColor: Boolean) -> Unit,
	onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit
) {
	SettingsDialogSectionTitle(text = "Theme") // TODO flyt string til String Resources
	Column(Modifier.selectableGroup()) {
		SettingsDialogThemeChooserRow(
			text = "Default", // TODO flyt string til String Resources
			selected = settings.brand == ThemeBrand.DEFAULT,
			onClick = { onChangeThemeBrand(ThemeBrand.DEFAULT) }
		)
		SettingsDialogThemeChooserRow(
			text = "Android", // TODO flyt string til String Resources
			selected = settings.brand == ThemeBrand.ANDROID,
			onClick = { onChangeThemeBrand(ThemeBrand.ANDROID) }
		)
	}
	if (settings.brand == ThemeBrand.DEFAULT && supportDynamicColor) {
		SettingsDialogSectionTitle(text = "Use Dynamic Color") // TODO flyt string til String Resources
		Column(Modifier.selectableGroup()) {
			SettingsDialogThemeChooserRow(
				text = "Yes", // TODO flyt string til String Resources
				selected = settings.useDynamicColor,
				onClick = { onChangeThemeBrand(ThemeBrand.ANDROID) }
			)
			SettingsDialogThemeChooserRow(
				text = "No", // TODO flyt string til String Resources
				selected = settings.useDynamicColor,
				onClick = { onChangeThemeBrand(ThemeBrand.ANDROID) }
			)
		}
	}
	SettingsDialogSectionTitle(text = "Dark mode preference") // TODO flyt string til String Resources
	Column(Modifier.selectableGroup()) {
		SettingsDialogThemeChooserRow(
			text = "System default", // TODO flyt string til String Resources
			selected = settings.darkThemeConfig == DarkThemeConfig.FOLLOW_SYSTEM,
			onClick = { onChangeDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM) }
		)
		SettingsDialogThemeChooserRow(
			text = "Light", // TODO flyt string til String Resources
			selected = settings.darkThemeConfig == DarkThemeConfig.LIGHT,
			onClick = { onChangeDarkThemeConfig(DarkThemeConfig.LIGHT) }
		)
		SettingsDialogThemeChooserRow(
			text = "Dark", // TODO flyt string til String Resources
			selected = settings.darkThemeConfig == DarkThemeConfig.DARK,
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
				),
			),
			onChangeThemeBrand = {},
			onChangeDynamicColorPreference = {},
			onChangeDarkThemeConfig = {},
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
			onChangeDarkThemeConfig = {}
		)
	}
}