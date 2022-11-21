package com.example.fantasy.presentation.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import com.example.fantasy.R
import com.example.fantasy.domain.repository.SignUpModel
import com.example.fantasy.presentation.viewModels.SignUpViewModel
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel
import java.util.prefs.Preferences

var obj = SignUpModel()

@Composable
fun SignUp(navController: NavController) {

    val viewModel = getViewModel<SignUpViewModel>()
    val signInFieldInfo = viewModel.fieldState.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff3D185B)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SignUpHeader()
        }

        item {
            NameField() {
                signInFieldInfo.fields.data!!.first_name = it
            }
        }

        item {
            LastNameField() {
                signInFieldInfo.fields.data!!.last_name = it
            }
        }

        item {
            EmailField() {
                signInFieldInfo.fields.data!!.email = it
            }
        }

        item {
            DropDownSignUp() {
                signInFieldInfo.fields.data!!.country = it
            }
        }

        item {
            UserNameField() {
                signInFieldInfo.fields.data!!.username = it

            }
        }

        item {
            PasswordField() {
                signInFieldInfo.fields.data!!.password = it
            }
        }

        item {
            SignInButton(navController) {
                viewModel.onSignupButtonClicked(signInFieldInfo, navController)
            }
        }

        item {
            SignInPageBottomImage()
        }
    }
}

@Composable
fun SignUpHeader() {
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
            text = "ثبت نام",
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
fun NameField(onTextChange: (String) -> Unit) {
    val name = remember {
        mutableStateOf("")
    }
    Text(text = "نام", style = normal400Size16TextStyle, color = Color(0xffFFFFFF))
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
            value = name.value,
            onValueChange = {
                name.value = it
                onTextChange(it)
            },
            singleLine = true
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun LastNameField(onTextChange: (String) -> Unit) {
    val lastName = remember {
        mutableStateOf("")
    }
    Text(text = "نام خانوادگی", style = normal400Size16TextStyle, color = Color(0xffFFFFFF))
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
            value = lastName.value,
            onValueChange = {
                lastName.value = it
                onTextChange(it)
            }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun EmailField(onTextChange: (String) -> Unit) {
    val email = remember {
        mutableStateOf("")
    }
    Text(text = "ایمیل", style = normal400Size16TextStyle, color = Color(0xffFFFFFF))
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
            value = email.value,
            onValueChange = {
                email.value = it
                onTextChange(it)
            }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun DropDownSignUp(onTextChange: (String) -> Unit) {
    var mExpanded by remember { mutableStateOf(false) }

    val mCities = listOf("America", "England", "Iran", "Canada", "Germany", "China", "Italy")

    var mSelectedText = remember { mutableStateOf("") }

    //var mTextFieldSize = remember { mutableStateOf(0) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Text(text = "کشور", style = normal400Size16TextStyle, color = Color(0xffFFFFFF))

    Column(Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = mSelectedText.value,
            onValueChange = {
                mSelectedText.value = it
                onTextChange(it)
            },
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xffA057DB), shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xff3D185B),
                textColor = Color(0xffFFFFFF)
            ),
            trailingIcon = {
                Icon(
                    icon,
                    contentDescription = "",
                    modifier = Modifier.clickable { mExpanded = !mExpanded },
                    tint = White
                )
            }
        )

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            mCities.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText.value = label
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
fun UserNameField(onTextChange: (String) -> Unit) {
    val username = remember {
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
            value = username.value,
            onValueChange = {
                username.value = it
                onTextChange(it)
            }
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun PasswordField(onTextChange: (String) -> Unit) {
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
                onTextChange(it)
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun SignInButton(navController: NavController, onClick: () -> Unit) {
    val brush = Brush.horizontalGradient(colors = listOf(Color(0xffCF31B9), Color(0xff9B3AF9)))
    Spacer(modifier = Modifier.height(51.dp))
    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(brush)
            .size(370.dp, 60.dp)
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
                text = "ثبت نام", style = normal700Size20TextStyle, color = Color(0xffFFFFFF)
            )
        }

    }
}

@Composable
fun SignInPageBottomImage() {
    Spacer(modifier = Modifier.height(81.dp))
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        painter = painterResource(R.drawable.bottomplayers),
        contentDescription = "drop_down_navbar",
        contentScale = ContentScale.FillBounds
    )
}

