package com.example.fantasy.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fantasy.R

@Composable
fun MyTeamNavBar(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .shadow(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(40.dp)
            .fillMaxSize()
            .background(Color(0xffFFFFFF))
    ) {
        var isDroppedDown = remember {
            mutableStateOf(false)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable image",
                            onClick = {
                                isDroppedDown.value = !isDroppedDown.value
                            }
                        ),
                    painter = painterResource(R.drawable.dropdown),
                    contentDescription = "drop_down_navbar",
                    contentScale = ContentScale.FillBounds
                )
            }
            if (isDroppedDown.value) {
                MyTeamDropdownDemo(isDroppedDown, navController)
            }
            Text(
                text = "تیم من",
                fontSize = 17.sp,
                color = Color(0xff18DEEA),
                style = normal800Size17TextStyle
            )

        }

    }
}