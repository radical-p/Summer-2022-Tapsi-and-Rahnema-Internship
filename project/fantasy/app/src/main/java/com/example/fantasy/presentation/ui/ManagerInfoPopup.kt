package com.example.fantasy.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.fantasy.R
import com.example.fantasy.domain.repository.ManagerInfo

@Composable
fun ManagerInfoPopup(managerInfo: ManagerInfo) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier =
            Modifier
                .padding(start = 16.dp, end = 17.dp, top = 185.dp, bottom = 179.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize()
                .background(Color(0xffFFFFFF)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ManagerInfoPopupImage()
            ManagerInfoPopupFollowButton()
            ManagerInfoPopupFields(ManagerInfo("محمدرضا رحیمی", null, 26, "ایران", "103"))
        }
    }
}

@Composable
fun ManagerInfoPopupImage() {
    Spacer(modifier = Modifier.height(46.dp))
    Image(
        painter = painterResource(R.drawable.testimage),
        contentDescription = null,
        modifier = Modifier
            .clip(CircleShape)
            .size(100.dp)
    )
    Spacer(modifier = Modifier.height(22.dp))
}

@Composable
fun ManagerInfoPopupFollowButton() {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff05D6E2)),
        modifier = Modifier
            .padding(horizontal = 86.dp)
            .height(40.dp),
    ) {
        Text(
            text = "دنبال کردن",
            style = normal800Size14TextStyle,
            color = Color(0xffFFFFFF),
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ManagerInfoPopupFields(managerInfo: ManagerInfo) {
    Text(
        text = "نام: ${managerInfo.fullName}",
        style = normal700Size14TextStyle,
        color = Color(0xff000000),
        modifier = Modifier.padding(top = 20.dp),
    )
    Text(
        text = "سن: ${managerInfo.age} سال ",
        style = normal700Size14TextStyle,
        color = Color(0xff000000),
        modifier = Modifier.padding(top = 10.dp),
    )
    Text(text = "کشور: ${managerInfo.country}",
        style = normal700Size14TextStyle,
        color = Color(0xff000000),
        modifier = Modifier.padding(top = 10.dp),
    )
    Text(text = "آخرین امتیاز: ${managerInfo.points}",
        style = normal700Size14TextStyle,
        color = Color(0xff000000),
        modifier = Modifier.padding(top = 10.dp),
    )
}