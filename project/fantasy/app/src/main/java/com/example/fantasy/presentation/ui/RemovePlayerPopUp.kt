package com.example.fantasy.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fantasy.R

@Composable
fun RemovePlayerPopUp() {
    Card(modifier = Modifier.size(200.dp, 100.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RemovePlayerPopUpHeader()
            RemovePlayerPopUpButtons(playerName = "Henderson")
        }

    }
}

@Composable
fun RemovePlayerPopUpHeader() {
    Box(modifier = Modifier
        .background(Color(0xff3D195B))
        .width(200.dp)) {
        Text(text = "حذف بازیکن", style = normal500Size12TextStyle, color = Color(0xff00FF87), modifier = Modifier.align(
            Alignment.Center))
    }
    Spacer(modifier = Modifier.height(3.dp))
    Image(
        painter = painterResource(R.drawable.shirt),
        contentDescription = "player-shirt",
        contentScale = ContentScale.FillBounds
    )
    Spacer(modifier = Modifier.height(3.dp))
}

@Composable
fun RemovePlayerPopUpButtons(playerName: String) {
    Text(text = "مطمین هستید؟ $playerName آیا از حذف ", style = normal700Size10TextStyle, color = Color(0xff3D195B))
    Row() {
        Button(onClick = { /*TODO*/ },
            border = BorderStroke(1.dp, Color(0xff3D195B)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFFFFFF)),
            contentPadding = PaddingValues(),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        ) {
            Text(text = "لغو", style = normal700Size10TextStyle, color = Color(0xff3D195B))
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = { /*TODO*/ },
            contentPadding = PaddingValues(),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffED1B5D)),
            ) {
            Text(text = "حذف", style = normal700Size10TextStyle, color = Color(0xffFFFFFF))
        }
    }
}