package com.example.fantasy.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Preview
@Composable
fun FailPopup() {
    Popup(
        alignment = Alignment.TopCenter,
        properties = PopupProperties()
    ) {

        Box(
            Modifier
                .size(300.dp, 100.dp)
                .padding(top = 5.dp)
                .background(Color(0xff933087), RoundedCornerShape(10.dp))
                .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "ورود با مشکل مواجه شد",

                    color = Color.White,

                    modifier = Modifier.padding(vertical = 5.dp),

                    fontSize = 16.sp
                )
            }
        }
    }
}