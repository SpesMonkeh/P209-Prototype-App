package com.example.p209prototypeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.p209prototypeapp.ui.theme.P209PrototypeAppTheme

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			P209PrototypeAppTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Greeting()
				}
			}
		}
	}
}

@Composable
fun Greeting() {
	val backgroundImage = R.drawable.baggrund
	var number by remember { mutableStateOf(1) }

	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Bottom
	) {
		Column(
			modifier = Modifier
				.padding(10.dp)
				.background(Color.White),
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

			Button(onClick = { TODO() }) {
				Text(text = "Profil")
			}
			Button(onClick = { TODO() } ) {
				Text(text = "Indstillinger")
			}

			Button(onClick = { TODO() } ) {
				Text(text = "Opskrifter")
			}

			Button(onClick = { TODO() } ) {
				Text(text = "Budget")
			}

			Button(onClick = { TODO() } ) {
				Text(text = "Tilbudsavis")
			}
		}
		DrawNavigationBar()
	}

}

@Composable
private fun DrawNavigationBar() {

	var selectedItem by remember { mutableStateOf(0) }
	val items = arrayOf("Profil", "Indstillinger")

	NavigationBar(tonalElevation = 20.dp) {
		items.forEachIndexed { index, item ->
			NavigationBarItem(
				icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
				label = { Text(item) },
				selected = selectedItem == index,
				onClick = { /*TODO*/ },
			)
		}

	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	P209PrototypeAppTheme {
		Greeting()
	}
}