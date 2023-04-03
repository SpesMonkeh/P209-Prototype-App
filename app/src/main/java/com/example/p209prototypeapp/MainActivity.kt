package com.example.p209prototypeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun GreetUser() {
	val user = "P209"

	Row(
		modifier = Modifier
			.background(Color(0xFF4FA23E))
			.fillMaxWidth()
			.padding(10.dp),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,

	) {
		Text(
			text = stringResource(R.string.greet_morning, user)
		)
	}
}

@Composable
fun DineroScreenNavigation(modifier: Modifier = Modifier) {

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