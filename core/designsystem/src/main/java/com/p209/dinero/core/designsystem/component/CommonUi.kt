package com.p209.dinero.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p209.dinero.core.designsystem.R
import com.p209.dinero.core.designsystem.theme.DineroTheme

@Composable
fun DineroNavigationBar(modifier: Modifier = Modifier) {
	var selectedItem by remember { mutableStateOf(0) }
	val items = arrayOf(
		stringResource(R.string.profile_screen_name),
		stringResource(R.string.indstillinger_DK)
	)

	NavigationBar(tonalElevation = 20.dp) {
		items.forEachIndexed { index, item ->
			NavigationBarItem(
				icon = { Icon(
					Icons.Filled.Favorite,
					tint = Color(0xFF963976),
					contentDescription = null
				) },
				label = { Text(item) },
				selected = selectedItem == index,
				onClick = { /*TODO*/ },
				modifier = Modifier.background(Color(0xFF4FA23E))
			)
		}
	}
}

@Preview(
	showBackground = true,
	name = "Bottom Navigation Bar"
)
@Composable
fun DineroNavBarPreview() {
	DineroTheme {
		DineroNavigationBar()
	}
}