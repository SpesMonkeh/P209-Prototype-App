package com.example.p209prototypeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.p209prototypeapp.ui.theme.P209PrototypeAppTheme

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			P209PrototypeAppTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					MainScreen()
				}
			}
		}
	}
}

@Composable
fun MainScreen() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.tertiary),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Bottom
	) {
		Column(
			modifier = Modifier.padding(10.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Top
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center
			) {
				Text(text = stringResource(R.string.app_name))
			}

			MainScreenButton(
				label = stringResource(R.string.profil_DK),
				onClick = { TODO() }
			)
			MainScreenButton(
				label = stringResource(R.string.indstillinger_DK),
				onClick = { TODO() }
			)
			MainScreenButton(
				label = stringResource(R.string.koeleskab_DK),
				onClick = { TODO() }
			)
			MainScreenButton(
				label = stringResource(R.string.opskrifter_DK),
				onClick = { TODO() }
			)
			MainScreenButton(
				label = stringResource(R.string.budget_DK),
				onClick = { TODO() }
			)
			MainScreenButton(
				label = stringResource(R.string.tilbudsavis_DK),
				onClick = { TODO() }
			)
		}
		DrawNavigationBar()
	}
}

@Composable
fun MainScreenButton(label: String, onClick: () -> Unit) {
	Button(onClick = onClick) {
		Text(label)
	}
}

@Composable
fun DrawNavigationBar() {
	var selectedItem by remember { mutableStateOf(0) }
	val items = arrayOf(
		stringResource(R.string.profil_DK),
		stringResource(R.string.indstillinger_DK)
	)

	NavigationBar(tonalElevation = 20.dp) {
		items.forEachIndexed { index, item ->
			NavigationBarItem(
				icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
				label = { Text(item) },
				selected = selectedItem == index,
				onClick = { /*TODO*/ },
				modifier = Modifier.background(MaterialTheme.colorScheme.inversePrimary)
			)
		}

	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	P209PrototypeAppTheme {
		MainScreen()
	}
}