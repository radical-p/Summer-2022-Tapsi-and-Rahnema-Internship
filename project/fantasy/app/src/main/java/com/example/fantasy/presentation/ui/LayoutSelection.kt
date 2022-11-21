package com.example.fantasy.presentation.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fantasy.R

@Composable
fun SelectLayoutComponent(
    currentState: Boolean,
    onCLick: (Boolean) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        RahnemaCollageLogo()
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 44.dp, end = 44.dp)
                .background(Color(0xffF7F7F7))
                .padding(horizontal = 5.dp, vertical = 3.dp)
                .height(40.dp)
        ) {
            SelectLayoutButton(layoutName = "شماتیک ترکیب", currentState) {
                onCLick(true)
            }
            SelectLayoutButton(layoutName = "مشاهده لیست", !currentState) {
                onCLick(false)
            }
        }
    }

}

@Composable
fun RowScope.SelectLayoutButton(layoutName: String, selectedButton: Boolean, onCLick: () -> Unit) {
    Button(onClick = { onCLick() }, colors = ButtonDefaults.buttonColors(backgroundColor = if(selectedButton) Color.White else Color(0xffF7F7F7)),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
    ) {
        Text(text = layoutName, color =  Color(0xff3D195B),
            style = if(selectedButton) normal700Size14TextStyle else normal400Size14TextStyle
        )
    }
}

@Composable
fun RahnemaCollageLogo() {
    Card(modifier = Modifier
        .size(75.dp, 72.dp).offset(y = 10.dp), backgroundColor = Color(0xffFFFFFF), elevation = 30.dp) {
        Image(modifier = Modifier.fillMaxSize(), painter = painterResource(R.drawable.rahnemacollagelogo),
            contentDescription = null, contentScale = ContentScale.FillBounds)
    }
}