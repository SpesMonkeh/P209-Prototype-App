package com.example.p209prototypeapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p209prototypeapp.R
import com.example.p209prototypeapp.composables.DrawIngredientCard
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

	Scaffold(
		topBar = { GreetUser() },
		bottomBar = { DrawNavigationBar() }

	) { contextPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(MaterialTheme.colorScheme.tertiary)
				.padding(contextPadding),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.SpaceEvenly
		) {
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
	}
}

@Composable
fun GreetUser() {
	val user = "Johnny Bib"

	Row(
		modifier = Modifier
			.background(Color(0xFF4FA23E))
			.fillMaxWidth()
			.padding(10.dp),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,

	) {
		Text(
			text = "Hello $user!"
		)
	}
}

@Composable
fun MainScreenButton(label: String, onClick: () -> Unit) {
	Button(onClick = onClick) {
		Text(
			text = label,
			fontSize = 24.sp
		)
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
	name = "Main Screen"
)
@Composable
fun MainScreenPreview() {
	P209PrototypeAppTheme {
		MainScreen()
	}
}

@Preview(
	showBackground = true,
	name = "Ingredient Card"
)
@Composable
fun IngredientCardPreview() {
	P209PrototypeAppTheme {
		DrawIngredientCard(Color(0xFF4FA23E))
	}
}