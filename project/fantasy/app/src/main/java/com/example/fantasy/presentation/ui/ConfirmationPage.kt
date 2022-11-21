package com.example.fantasy.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fantasy.R
import com.example.fantasy.presentation.viewModels.VerificationViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ConfirmationPage(navController: NavController) {

    val viewModel = getViewModel<VerificationViewModel>()
    val verificationFieldInfo = viewModel.fieldState.collectAsState().value

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xff3D185B)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ConfirmationPageHeader()
        ConfirmationPageTextField() {
            verificationFieldInfo.fields.data!!.code = it
        }
        ConfirmationPageConfirmButton(navController){
            viewModel.onVerificationButtonClicked(verificationFieldInfo, navController)
        }
        ConfirmationPageBottom()
    }
}

@Composable
fun ConfirmationPageHeader() {
    Row() {
        Divider(
            thickness = 4.dp, modifier =
            Modifier
                .padding(vertical = 52.dp, horizontal = 16.dp)
                .width(70.dp)
                .background(Color(0xff933087))
        )
        Text(
            modifier =
            Modifier
                .padding(vertical = 30.dp, horizontal = 25.dp),
            text = "تایید ثبت نام",
            style = normal400Size24TextStyle,
            color = Color(0xffFFFFFF)
        )
        Divider(
            thickness = 4.dp, modifier =
            Modifier
                .padding(vertical = 52.dp, horizontal = 16.dp)
                .width(70.dp)
                .background(Color(0xff9B3AF9))
        )
    }
    Spacer(modifier = Modifier.height(80.dp))
}

@Composable
fun ConfirmationPageTextField(onTextChange: (String) -> Unit) {
    val text1 = "لطفا کدی که به ایمیلتان ارسال شده را در"
    val text2 = "کادر زیر وارد کنید"
    val confirmationCode = remember {
        mutableStateOf("")
    }

    Text(
        text = text1,
        style = normal600Size16TextStyle,
        color = Color(0xffFFFFFF),
        textAlign = TextAlign.Center
    )
    Text(
        text = text2,
        style = normal600Size16TextStyle,
        color = Color(0xffFFFFFF),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(20.dp))

    TextField(modifier =
    Modifier
        .padding(horizontal = 20.dp)
        .border(width = 1.dp, color = Color(0xffA057DB), shape = RoundedCornerShape(10.dp))
        .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xff3D185B),
            textColor = Color(0xffFFFFFF)
        ),
        value = confirmationCode.value,
        onValueChange = {
            confirmationCode.value = it
            onTextChange(it)
        }
    )

    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
fun ConfirmationPageConfirmButton(navController: NavController, onClick: () -> Unit) {
    val brush = Brush.horizontalGradient(colors = listOf(Color(0xffCF31B9), Color(0xff9B3AF9)))

    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(brush)
            .size(350.dp, 60.dp)
    ) {
        Button(
            onClick = onClick,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Text(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp)
                    .background(brush),
                textAlign = TextAlign.Center,
                text = "تایید ثبت نام", style = normal700Size20TextStyle, color = Color(0xffFFFFFF)
            )
        }
    }
}

@Composable
fun ConfirmationPageBottom() {
    Spacer(modifier = Modifier.height(50.dp))

    Image(
        alignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        painter = painterResource(R.drawable.bottomplayers),
        contentDescription = "drop_down_navbar",
        contentScale = ContentScale.FillBounds
    )
}