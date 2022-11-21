package com.example.fantasy.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fantasy.R
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.presentation.viewModels.DeferredData

@Composable
fun PlayerAndMoneyRemaining(
    dashboard: DeferredData<Dashboard>,
    ) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(86.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
                .offset(y = 10.dp)
            //.align(Alignment.CenterVertically), Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(R.drawable.remaining_player),
                contentDescription = "remaining player",
                contentScale = ContentScale.FillBounds,
            )
            when(dashboard) {
                is DeferredData.Error -> {
                    Text(
                        "", modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterEnd),
                        fontSize = 18.sp,
                        color = Color(0xff3D195B),
                        style = normal900Size18TextStyle
                    )
                }
                is DeferredData.Loading -> {
                    Text(
                        "", modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterEnd),
                        fontSize = 18.sp,
                        color = Color(0xff3D195B),
                        style = normal900Size18TextStyle
                    )
                }
                is DeferredData.Loaded -> {
                    Text(
                        "${dashboard.data.remainingPlayers}/15", modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterEnd),
                        fontSize = 18.sp,
                        color = Color(0xff3D195B),
                        style = normal900Size18TextStyle
                    )
                }
            }

        }
        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
                .offset(y = 10.dp)
                .align(Alignment.CenterVertically), Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(R.drawable.remaining_money),
                contentDescription = "remaining money",
                contentScale = ContentScale.FillBounds,
            )
            when(dashboard) {
                is DeferredData.Error -> {
                    Text(
                        text = "", modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterEnd),
                        fontSize = 18.sp,
                        color = Color(0xff3D195B),
                        style = normal900Size18TextStyle
                    )
                }
                is DeferredData.Loading -> {
                    Text(
                        text = "", modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterEnd),
                        fontSize = 18.sp,
                        color = Color(0xff3D195B),
                        style = normal900Size18TextStyle
                    )
                }
                is DeferredData.Loaded -> {
                    Text(
                        text = dashboard.data.credits, modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.CenterEnd),
                        fontSize = 18.sp,
                        color = Color(0xff3D195B),
                        style = normal900Size18TextStyle
                    )
                }
            }
        }
    }
}