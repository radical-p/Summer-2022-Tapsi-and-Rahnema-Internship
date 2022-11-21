package com.example.fantasy.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fantasy.domain.repository.DateAndWeek
import com.example.fantasy.presentation.viewModels.DateAndWeekViewModel
import com.example.fantasy.presentation.viewModels.DeferredData
import org.koin.androidx.compose.getViewModel
import java.lang.StringBuilder
import android.app.DatePickerDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DateAndWeek() {

    val viewModel = getViewModel<DateAndWeekViewModel>()
    val dateAndWeekInfo = viewModel.dateandweek.collectAsState().value

    val week: String? = dateAndWeekInfo.data?.week
    val date: String? = dateAndWeekInfo.data?.date

    when (dateAndWeekInfo) {
        is DeferredData.Error ->
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .shadow(10.dp)
                    .background(Color(0xff3D195B))
                    .height(35.dp)
                    .fillMaxWidth()
            ) {
                Button(modifier = Modifier.fillMaxSize(), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent), onClick = {
                    viewModel.getDateAndWeek()
                }) {
                    Text(text = "تلاش مجدد", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, color = Color(0xffFFFFFF))
                }
            }
        is DeferredData.Loaded ->
            DateAndWeekUi("هفته" + " " + digitMiner(week), epochHandler(date))

        is DeferredData.Loading ->
            DateAndWeekUi("", "")
    }
}

@Composable
fun DateAndWeekUi(week: String?, date: String?) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(5.dp))
            .shadow(10.dp)
            .background(Color(0xff3D195B))
            .height(35.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.5f)
                .padding(5.dp),
            Alignment.Center
        ) {
            Text(
                text = week ?: "NULL",
                fontSize = 14.sp,
                color = Color(0xff00FF87),
                style = normal800Size14TextStyle
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.5f),
            Alignment.Center
        ) {
            Text(
                text = date ?: "NULL",
                fontSize = 14.sp,
                color = Color(0xffF7F7F7),
                style = normal800Size14TextStyle
            )
        }
    }
}

fun digitMiner(week: String?): String {

    val builder = StringBuilder()
    val number: String =  if(week!!.length == 10) {
        week[9].toString()
    }
    else {
        builder.append(week[9]).append(week[10])
        builder.toString()
    }
    return engToPerDigitMapper(number)
}

fun engToPerDigitMapper(digit: String):String{
    val persianNumber: String
    when(digit){
        "1" -> persianNumber = "اول"
        "2" -> persianNumber = "دوم"
        "3" -> persianNumber = "سوم"
        "4" -> persianNumber = "چهارم"
        "5" -> persianNumber = "پنجم"
        "6" -> persianNumber = "ششم"
        "7" -> persianNumber = "هفتم"
        "8" -> persianNumber = "هشتم"
        "9" -> persianNumber = "نهم"
        "10" -> persianNumber = "دهم"
        "11" -> persianNumber = "یازدهم"
        "12" -> persianNumber = "دوازدهم"
        "13" -> persianNumber = "سیزدهم"
        "14" -> persianNumber = "چهاردهم"
        else -> persianNumber = "هندل نکردم"
    }
    return persianNumber
}

fun epochHandler(epoch: String?): String{
    val year: Int = epoch!!.substring(0, 4).toInt()
    val month: Int = epoch.substring(5, 7).toInt()
    val day: Int = epoch.substring(8, 10).toInt()

    val hour: String = epoch.substring(11, 13)

    val myList: IntArray = gregorian_to_jalali(year, month, day)

    return myList[2].toString()+ " " + monthToText(myList[1]) + " " + myList[0].toString() + " " + "-"+ " ساعت " + hour
}


fun gregorian_to_jalali(gy: Int, gm: Int, gd: Int): IntArray {
    var g_d_m: IntArray = intArrayOf(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
    var gy2: Int = if (gm > 2) (gy + 1) else gy
    var days: Int = 355666 + (365 * gy) + ((gy2 + 3) / 4).toInt() - ((gy2 + 99) / 100).toInt() + ((gy2 + 399) / 400).toInt() + gd + g_d_m[gm - 1]
    var jy: Int = -1595 + (33 * (days / 12053).toInt())
    days %= 12053
    jy += 4 * (days / 1461).toInt()
    days %= 1461
    if (days > 365) {
        jy += ((days - 1) / 365).toInt()
        days = (days - 1) % 365
    }
    var jm: Int; var jd: Int;
    if (days < 186) {
        jm = 1 + (days / 31).toInt()
        jd = 1 + (days % 31)
    } else {
        jm = 7 + ((days - 186) / 30).toInt()
        jd = 1 + ((days - 186) % 30)
    }
    return intArrayOf(jy, jm, jd)
}

fun monthToText(month: Int): String{
    val persianMonth: String = when(month){
        1 -> "فروردین"
        2 -> "اردیبهشت"
        3 -> "خرداد"
        4 -> "تیر"
        5 -> "مرداد"
        6 -> "شهریور"
        7 -> "مهر"
        8 -> "هشتم"
        9 -> "نهم"
        10 -> "دهم"
        11 -> "یازدهم"
        12 -> "دوازدهم"
        else -> "هندل نکردم"
    }
    return persianMonth
}