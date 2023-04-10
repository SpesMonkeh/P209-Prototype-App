package com.p209.dinero.core.designsystem.component

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
import com.p209.dinero.core.designsystem.theme.DineroTheme
import com.p209.dinero.core.designsystem.theme.Purple80

data class FoodType(val name: String = "[EMPTY]")

@Composable
fun DrawIngredientCard(ingredient: FoodType = FoodType(), color: Color = Purple80) {
	Surface(
		color = color
	) {
		Row(
			horizontalArrangement = Arrangement.Start,
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp)
		) {

			Text(text = ingredient.name)
		}
	}
}

@Preview(
	showBackground = true,
	name = "Ingredient Card"
)
@Composable
fun IngredientCardPreview() {
	DineroTheme {
		DrawIngredientCard()
	}
}