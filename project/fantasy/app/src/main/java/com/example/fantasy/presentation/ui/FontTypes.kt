package com.example.fantasy.presentation.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fantasy.R

private val fontFamily = FontFamily(
    Font(R.font.vazirmatn_medium, FontWeight.Normal),
    Font(R.font.vazirmatn_black, FontWeight.Black),
    Font(R.font.vazirmatn_bold, FontWeight.Bold),
    Font(R.font.vazirmatn_light, FontWeight.Light),
    //Font(R.font.vazirmatn_extrabold, FontWeight.ExtraBold),
    Font(R.font.vazirmatn_thin, FontWeight.ExtraLight),
    Font(R.font.vazirmatn_semibold, FontWeight.SemiBold)
)

val normal300Size12TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W300, fontSize = 12.sp)
val normal500Size12TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W500, fontSize = 12.sp)
val normal400Size12TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W400, fontSize = 12.sp)
val normal700Size14TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W700, fontSize = 14.sp)
val normal400Size14TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W400, fontSize = 14.sp)
val normal800Size14TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W800, fontSize = 14.sp)
val normal800Size17TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W800, fontSize = 17.sp)
val normal900Size17TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W900, fontSize = 14.sp)
val normal900Size18TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W900, fontSize = 18.sp)
val normal300Size14TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W300, fontSize = 14.sp)
val normal700Size11TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W700, fontSize = 11.sp)
val normal700Size10TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W700, fontSize = 10.sp)
val normal400Size9TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W400, fontSize = 9.sp)
val normal800Size18TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W800, fontSize = 18.sp)
val normal400Size24TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W400, fontSize = 24.sp)
val normal400Size16TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W400, fontSize = 16.sp)
val normal700Size20TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W700, fontSize = 20.sp)
val normal300Size20TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W300, fontSize = 20.sp)
val normal600Size16TextStyle =
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.W600, fontSize = 16.sp)