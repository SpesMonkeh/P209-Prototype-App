package com.example.p209prototypeapp.functions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar

class AppTime() {
	val calendar = Calendar.getInstance()
	val year = calendar.get(Calendar.YEAR)
	val month = calendar.get(Calendar.MONTH)
	val date = calendar.get(Calendar.DATE)
	val weekday = calendar.get(Calendar.DAY_OF_WEEK)
	val hour = calendar.get(Calendar.HOUR)
	val minute = calendar.get(Calendar.MINUTE)
	val second = calendar.get(Calendar.SECOND)
	val time = calendar.time
}


@Preview(
	showBackground = true,
	name = "Time Preview"
)
@Composable
fun AppTimePreviewer()
{
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "-- TIME --\n" + AppTime().time.toString(),
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(15.dp))

		Text(
			text = "-- YEAR --\n" + AppTime().year.toString(),
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(15.dp))

		Text(
			text = "-- MONTH --\n" + AppTime().month.toString(),
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(15.dp))

		Text(
			text = "-- DATE --\n" + AppTime().date.toString(),
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(15.dp))

		Text(
			text = "-- WEEKDAY --\n" + AppTime().weekday.toString(),
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(15.dp))

		Text(
			text = "-- HOUR --\n" + AppTime().hour.toString(),
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(15.dp))

		Text(
			text = "-- MINUTE --\n" + AppTime().minute.toString(),
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(15.dp))

		Text(
			text = "-- SECOND --\n" + AppTime().second.toString(),
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(15.dp))

		Text(
			text = "-- GREETING --\n" + GetGreeting(),
			textAlign = TextAlign.Center
		)
	}
}


fun GetGreeting(): String = if (AppTime().hour in 1..3) "Good night!"
else "Hello"