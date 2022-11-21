package com.example.fantasy.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fantasy.R

@Composable
fun Header() {
    Row() {
        Box(
            modifier = Modifier
                .weight(1f, true)
                .height(130.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(R.drawable.android_asset),
                contentDescription = "background_image",
                contentScale = ContentScale.FillBounds
            )
            Row() {
                /*Image(
                    modifier = Modifier
                        .height(150.dp)
                        .width(250.dp)
                        .weight(0.6f),
                    painter = painterResource(R.drawable.headerplayers),
                    contentDescription = "player_image",
                    contentScale = ContentScale.FillBounds
                )*/
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .align(Alignment.CenterVertically)
                ) {

                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .weight(0.65f)
                            .padding(top = 18.dp, bottom = 13.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .height(59.dp)
                                .width(46.dp),
                            //.padding(top = 15.dp),
                            painter = painterResource(R.drawable.premierlogo),
                            contentDescription = "premier_logo",
                            contentScale = ContentScale.FillBounds,

                            )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .weight(0.35f)
                    ) {
                        Image(
                            modifier = Modifier
                                .height(24.dp)
                                .width(106.dp),
                            //.padding(bottom = 15.dp),
                            //.padding(buttom =  0.dp, up = 16.dp),
                            painter = painterResource(R.drawable.headertext),
                            contentDescription = "header_text",
                            contentScale = ContentScale.FillBounds,

                            )
                    }
                }
                Image(
                    modifier = Modifier
                        .height(150.dp)
                        .width(250.dp)
                        .weight(0.6f),
                    painter = painterResource(R.drawable.headerplayers),
                    contentDescription = "player_image",
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }

}