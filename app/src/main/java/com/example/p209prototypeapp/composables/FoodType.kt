package com.example.p209prototypeapp.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.p209prototypeapp.ui.theme.P209PrototypeAppTheme

data class FoodType(val name: String)

@Composable
fun DrawIngredientCard(color: Color = Color.LightGray) {
	val foodType = FoodType(name = "Tomato")

	Surface(
		color = Color.Green
	) {
		Row(
			horizontalArrangement = Arrangement.Start,
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp)
		) {

			Text(text = foodType.name)
		}
	}
}