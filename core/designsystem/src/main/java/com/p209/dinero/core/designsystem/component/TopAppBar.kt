package com.p209.dinero.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.p209.dinero.core.designsystem.icon.DineroIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DineoTopAppBar(
	@StringRes titleResource: Int,
	@DrawableRes navigationIcon: Int, // ImageVector type i Now in Android
	navigationIconContentDescription: String?,
	@DrawableRes actionIcon: Int, // ImageVector type i Now in Android
	actionIconContentDescription: String?,
	modifier: Modifier = Modifier,
	colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
	onNavigationClick: () -> Unit = { },
	onActionClick: () -> Unit = { }
) {
	CenterAlignedTopAppBar(
		title = { Text(stringResource(titleResource)) },
		navigationIcon = {
			IconButton(onClick = onNavigationClick) {
				Icon(
					painter = painterResource(navigationIcon),
					contentDescription = navigationIconContentDescription,
					tint = MaterialTheme.colorScheme.onSurface
				)
			}
		},
		actions = {
			IconButton(onClick = onActionClick) {
				Icon(
					painter = painterResource(actionIcon),
					contentDescription = actionIconContentDescription,
					tint = MaterialTheme.colorScheme.onSurface
				)
			}
		},
		colors = colors,
		modifier =  modifier.testTag("DineroTopAppBar") // TODO Kun nødvendig, hvis vi laver unit testing
	)
}

/**
 * *UI element*
 *
 * Top app bar med action-knap i højre side.
 *
 * `
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DineoTopAppBar(
	@StringRes titleResource: Int,
	@DrawableRes actionIcon: Int, // ImageVector type i Now in Android
	actionIconContentDescription: String?,
	modifier: Modifier = Modifier,
	colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
	onActionClick: () -> Unit = { }
) {
	CenterAlignedTopAppBar(
		title = { Text(stringResource(titleResource)) },
		actions = {
			IconButton(onClick = onActionClick) {
				Icon(
					painter = painterResource(actionIcon),
					contentDescription = actionIconContentDescription,
					tint = MaterialTheme.colorScheme.onSurface
				)
			}
		},
		colors = colors,
		modifier = modifier.testTag("DineroTopAppBar") // TODO Kun nødvendig, hvis vi laver unit testing
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Dinero Top App Bar")
@Composable
private fun DineroTopAppBarPreview() {
	DineoTopAppBar(
		titleResource = android.R.string.untitled,
		navigationIcon = DineroIcons.TEST_chefs_hat,
		navigationIconContentDescription = "Navigation icon",
		actionIcon = DineroIcons.TEST_chefs_hat,
		actionIconContentDescription = "Action icon"
	)
}