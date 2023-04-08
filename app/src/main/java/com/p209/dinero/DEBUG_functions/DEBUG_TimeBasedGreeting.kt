package com.p209.dinero.DEBUG_functions
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.material3.Switch
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import java.text.SimpleDateFormat
//import java.util.Calendar
//import java.util.Date
//import java.util.Locale
//
//class AppTime {
//	val calendar: Calendar = Calendar.getInstance()
//
//	private val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.US)
//	private val formatterDateTime: String = dateFormat.format(calendar.time)
//
//	val year = calendar.get(Calendar.YEAR)
//	val month = calendar.get(Calendar.MONTH)
//	val date = calendar.get(Calendar.DATE)
//	val weekday = calendar.get(Calendar.DAY_OF_WEEK)
//	val hour = calendar.get(Calendar.HOUR)
//	val hour24 = calendar.get(Calendar.HOUR_OF_DAY)
//	val minute = calendar.get(Calendar.MINUTE)
//	val second = calendar.get(Calendar.SECOND)
//	val time24: Date = calendar.time
//	val time: String = formatterDateTime
//}
//
//fun GetHourBasedOnFormat(use24HourFormat: Boolean): Int {
//	return if (use24HourFormat)
//		AppTime().hour24
//	else
//		AppTime().hour
//}
//
//fun GetTimeBasedOnFormat(use24HourFormat: Boolean): String {
//	return if (use24HourFormat)
//		AppTime().time24.toString()
//	else
//		AppTime().time
//}
//
//
//@Preview(
//	showBackground = true,
//	name = "Time Preview"
//)
//@Composable
//fun AppTimePreviewer()
//{
//	var use24HourFormat by remember { mutableStateOf(true) }
//
//	Column(
//		modifier = Modifier.fillMaxSize(),
//		verticalArrangement = Arrangement.Center,
//		horizontalAlignment = Alignment.CenterHorizontally
//	) {
//		Text(
//			text = "-- TIME --\n" + GetTimeBasedOnFormat(use24HourFormat),
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Text(
//			text = "-- YEAR --\n" + AppTime().year.toString(),
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Text(
//			text = "-- MONTH --\n" + AppTime().month.toString(),
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Text(
//			text = "-- DATE --\n" + AppTime().date.toString(),
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Text(
//			text = "-- WEEKDAY --\n" + AppTime().weekday.toString(),
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Text(
//			text = "-- HOUR --\n" + GetHourBasedOnFormat(use24HourFormat).toString(),
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Text(
//			text = "-- MINUTE --\n" + AppTime().minute.toString(),
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Text(
//			text = "-- SECOND --\n" + AppTime().second.toString(),
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Text(
//			text = "-- GREETING --\n" + GetGreeting(),
//			textAlign = TextAlign.Center
//		)
//
//		Text(
//			text = "==========================",
//			textAlign = TextAlign.Center
//		)
//
//		Spacer(Modifier.height(15.dp))
//
//		Row(
//			modifier = Modifier.fillMaxWidth(),
//			horizontalArrangement = Arrangement.Center,
//			verticalAlignment = Alignment.CenterVertically
//		) {
//			Text(
//				text = "Use 24-hour format?"
//			)
//
//			Switch(
//				checked = use24HourFormat,
//				onCheckedChange = { use24HourFormat = it }
//			)
//		}
//	}
//}
//
//
//fun GetGreeting(): String {
//
//	return when (AppTime().hour24) {
//		1 or 2 or 3 -> return "Good night!"
//		4 or 5 or 6 -> return "Rise and shine!"
//		7 or 8 or 9 -> return "Have a great day!"
//		10 or 11 or 12 -> return "Good noon!"
//		13 or 14 or 15 -> return "Hope your day is going well!"
//		16 or 17 or 18 -> return "Good afternoon!"
//		19 or 20 or 21 -> return "Good evening!"
//		22 or 23 or 24 -> return "Have a good night!"
//		else -> "Hello!"
//	}
//}