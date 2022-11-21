package com.example.fantasy.presentation.ui

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fantasy.R
import com.example.fantasy.presentation.viewModels.LogInViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SignInPage(navController: NavController) {
    val viewModel = getViewModel<LogInViewModel>()
    val logInFieldInfo = viewModel.fieldState.collectAsState().value
    val context = LocalContext.current

    if(logInFieldInfo.isLoggedIn) {
        navController.navigate(Screen.Home.route)
        logInFieldInfo.isLoggedIn = false
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color(0xff3D185B)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        item { SignInPageHeader() }
        item {
            SignInPageNameFields() {
                logInFieldInfo.fields.data!!.username = it
                if(it == "") {
                    ShowUsernameError(context)
                }
            }
        }
        item {
            SignInPagePasswordFields() {
                if(it == "") {
                    ShowPasswordError(context)
                }
                logInFieldInfo.fields.data!!.password = it
            }
        }
        item {
            SignInPageButtons(navController) {
                viewModel.onLoginButtonClicked(logInFieldInfo, navController)
            }
        }
        item { SignInPageBottom() }
    }
}

@Composable
fun SignInPageHeader() {
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
            text = "ورود به فانتزی",
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
}

@Composable
fun SignInPageNameFields(onNameChanged: (String) -> Unit) {
    val name = remember {
        mutableStateOf("")
    }
    Text(text = "نام کاربری", style = normal400Size16TextStyle, color = Color(0xffFFFFFF))
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    Box(
        Modifier
            .padding(horizontal = 20.dp)
    ) {
        TextField(modifier =
        Modifier
            .border(width = 1.dp, color = Color(0xffA057DB), shape = RoundedCornerShape(10.dp))
            .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xff3D185B),
                textColor = Color(0xffFFFFFF)
            ),
            value = name.value,
            onValueChange = {
                name.value = it
                onNameChanged(it)
            }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun SignInPagePasswordFields(onPasswordChanged: (String) -> Unit) {
    val password = remember {
        mutableStateOf("")
    }
    Text(text = "رمز عبور", style = normal400Size16TextStyle, color = Color(0xffFFFFFF))
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    Box(
        Modifier
            .padding(horizontal = 20.dp)
    ) {
        TextField(
            modifier =
            Modifier
                .border(width = 1.dp, color = Color(0xffA057DB), shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xff3D185B),
                textColor = Color(0xffFFFFFF)
            ),
            value = password.value,
            onValueChange = {
                password.value = it
                onPasswordChanged(it)
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

@Composable
fun SignInPageButtons(navController: NavController, onClick: () -> Unit) {
    val brush = Brush.horizontalGradient(colors = listOf(Color(0xffCF31B9), Color(0xff9B3AF9)))

    Spacer(modifier = Modifier.padding(vertical = 25.dp))

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
                text = "ورود", style = normal700Size20TextStyle, color = Color(0xffFFFFFF)
            )
        }
    }

    Spacer(modifier = Modifier.padding(vertical = 15.dp))

    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xff3D185B))
            .size(350.dp, 60.dp)
    ) {
        Button(
            onClick = {
                navController.navigate(Screen.SignUp.route)
            },
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
                    .border(
                        width = 2.dp,
                        color = Color(0xffA057DB),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxSize()
                    .padding(top = 13.dp),
                textAlign = TextAlign.Center,
                text = "ثبت نام", style = normal300Size20TextStyle, color = Color(0xffFFFFFF)
            )
        }
    }

    Spacer(modifier = Modifier.height(25.dp))
}

@Composable
fun SignInPageBottom() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        painter = painterResource(R.drawable.bottomplayers),
        contentDescription = "drop_down_navbar",
        contentScale = ContentScale.FillBounds
    )
}

fun ShowPasswordError(context: Context) {
    val passToast = Toast.makeText(
        context,
        "پسورد نباید خالی باشد.",
        Toast.LENGTH_LONG,
    )
    passToast.setGravity(Gravity.CENTER, 0, 200)
    passToast.show()
}

fun ShowUsernameError(context: Context) {
    val usernameToast = Toast.makeText(
        context,
        "نام کاربری نباید خالی باشد.",
        Toast.LENGTH_LONG,
    )
    usernameToast.setGravity(Gravity.CENTER, 0, 200)
    usernameToast.show()
}