package com.example.fantasy.presentation.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fantasy.R
import com.example.fantasy.domain.repository.ManagerProfileInfo
import com.example.fantasy.domain.repository.ManagerProfileRequest
import com.example.fantasy.presentation.viewModels.DeferredData
import com.example.fantasy.presentation.viewModels.ManagerProfileViewModel
//import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.getViewModel

@Composable
fun ProfilePage(navController: NavController) {

    val managerProfileViewModel = getViewModel<ManagerProfileViewModel>()
    val managerProfile = managerProfileViewModel.managerProfile.collectAsState().value

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xffFFFFFF)).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Header()
            Spacer(modifier = Modifier.height(16.dp))
            ProfileNavBar(navController)
            Spacer(modifier = Modifier.height(26.dp))
            when(managerProfile) {
                is DeferredData.Error -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff3D195B)), onClick = {
                            managerProfileViewModel.getProfile()
                        }) {
                            Text(text = "تلاش مجدد", color = Color(0XffFFFFFF))
                        }
                    }
                }
                is DeferredData.Loading -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(color = Color(0xff18DEEA))
                    }
                }
                is DeferredData.Loaded -> {

                    var editState by remember {
                        mutableStateOf(false)
                    }

                    when(editState) {
                        true -> {
                            ProfileEdit(managerProfile) {
                                managerProfileViewModel.updateProfile(it)
                                editState = false
                            }
                        }
                        false -> {
                            ProfileInfo(managerProfile) {
                                editState = true
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileNavBar(navController: NavController) {
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
                ProfileDropdownDemo(isDroppedDown, navController)
            }
            Text(
                text = "پروفایل",
                fontSize = 17.sp,
                color = Color(0xff18DEEA),
                style = normal800Size17TextStyle
            )
        }
    }
}

@Composable
fun ProfileDropdownDemo(isDroppedDown: MutableState<Boolean>, navController: NavController) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("نقل و انتقالات", "آخرین رویدادها", "تیم من")
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.White
                ),
            expanded = expanded,
            onDismissRequest = { expanded = false
                isDroppedDown.value = false },
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    when(selectedIndex) {
                        0 -> {

                        }
                        1 -> {
                            navController.navigate(Screen.Feed.route)
                        }
                        2 -> {
                            navController.navigate(Screen.Home.route)
                        }
                    }
                }) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = s,
                            fontSize = 17.sp,
                            color = Color(0xff3D195B),
                            style = normal900Size17TextStyle,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileInfo(
    managerProfile: DeferredData<ManagerProfileInfo>,
    onClick: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        GlideImage(
//            imageModel = managerProfile.data?.imageUrl,
//            modifier = Modifier.clip(CircleShape).size(80.dp),
//            loading = {
//                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
//            },
//            failure = {
//                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
//            }
//        )
        FirstNameFields(firstName = managerProfile.data?.firstName!!)
        LastNameFields(lastName = managerProfile.data?.lastName!!)
        EmailFields(email = managerProfile.data?.email!!)
        CountryFields(country = managerProfile.data?.country!!)
        UsernameFields(username = managerProfile.data?.username!!)
        PasswordFields()
        EditButton() {
            onClick()
        }
    }
}

@Composable
fun FirstNameFields(firstName: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "نام", style = normal400Size16TextStyle, color = Color(0xff333333))
    Spacer(modifier = Modifier.height(2.dp))
    Text(text = firstName, style = normal800Size17TextStyle, color = Color(0xff3D195B))
}

@Composable
fun LastNameFields(lastName: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "نام خانوادگی", style = normal400Size16TextStyle, color = Color(0xff333333))
    Spacer(modifier = Modifier.height(6.dp))
    Text(text = lastName, style = normal800Size17TextStyle, color = Color(0xff3D195B))
}

@Composable
fun EmailFields(email: String) {
    Spacer(modifier = Modifier.height(23.dp))
    Text(text = "ایمیل", style = normal400Size16TextStyle, color = Color(0xff333333))
    Spacer(modifier = Modifier.height(5.dp))
    Text(text = email, style = normal800Size17TextStyle, color = Color(0xff3D195B))
}

@Composable
fun CountryFields(country: String) {
    Spacer(modifier = Modifier.height(18.dp))
    Text(text = "کشور", style = normal400Size16TextStyle, color = Color(0xff333333))
    Spacer(modifier = Modifier.height(4.dp))
    Text(text = country, style = normal800Size17TextStyle, color = Color(0xff3D195B))
}

@Composable
fun UsernameFields(username: String) {
    Spacer(modifier = Modifier.height(22.dp))
    Text(text = "نام کاربری", style = normal400Size16TextStyle, color = Color(0xff333333))
    Spacer(modifier = Modifier.height(5.dp))
    Text(text = username, style = normal800Size17TextStyle, color = Color(0xff3D195B))
}

@Composable
fun PasswordFields() {
    Spacer(modifier = Modifier.height(11.dp))
    Text(text = "رمزعبور", style = normal400Size16TextStyle, color = Color(0xff333333))
    Spacer(modifier = Modifier.height(11.dp))
    Text(text = "*******", style = normal800Size17TextStyle, color = Color(0xff3D195B))
}

@Composable
fun EditButton(onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(50.dp))
    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xffFFFFFF))
            .size(328.dp, 48.dp)
    ) {
        Button(
            onClick = {
                      onClick()
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
                        color = Color(0xff3D195B),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxSize()
                    .padding(top = 13.dp),
                textAlign = TextAlign.Center,
                text = "ویرایش", style = normal600Size16TextStyle, color = Color(0xff3D195B)
            )
        }
    }
    Spacer(modifier = Modifier.height(265.dp))
}

@Composable
fun ProfileEdit(
    managerProfile: DeferredData<ManagerProfileInfo>,
    onClick: (ManagerProfileRequest) -> Unit,
) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var country by remember {
        mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ProfileEditPicture()
        ProfileEditField(title = "نام", managerProfile.data?.firstName!!) {
            firstName = it
        }
        ProfileEditField(title = "نام خانوادگی", managerProfile.data?.lastName!!) {
            lastName =it
        }
        ProfileEditField(title = "ایمیل", managerProfile.data?.email!!) {
            email = it
        }
        ProfileEditField(title = "کشور", managerProfile.data?.country!!) {
            country = it
        }
        ProfileEditField(title = "نام کاربری", managerProfile.data?.username!!) {
            username = it
        }
        ProfileEditField(title = "رمزعبور", "") {
            password = it
        }
        ProfileEditConfirmButton() {
            onClick(ManagerProfileRequest(firstName, lastName, email, country, username, password, null))
        }
    }
}

@Composable
fun ProfileEditPicture() {
    Image(
        painter = painterResource(R.drawable.testimage),
        contentDescription = null,
        modifier = Modifier
            .clip(CircleShape)
            .size(80.dp)
    )
    Button(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 102.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffEBFF00)),
        onClick = { /*TODO*/ },
    ) {
        Text(
            text = "بارگزاری تصویر",
            style = normal400Size14TextStyle,
            color = Color(0xff3D195B),
        )
    }
}

@Composable
fun ProfileEditField(title: String, text: String, onTextChanged: (String) -> Unit) {
    var currentName by remember {
        mutableStateOf(text)
    }
    Spacer(modifier = Modifier.height(24.dp))
    Text(text = title, style = normal400Size16TextStyle, color = Color(0xff333333))
    Spacer(modifier = Modifier.height(5.dp))
    TextField(
        modifier = Modifier
            .padding(horizontal = 17.dp, vertical = 5.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xff333333),
            backgroundColor = Color(0xffF4F4F4),
            cursorColor = Color(0xff333333),
            focusedIndicatorColor = Color(0xffF4F4F4),
            disabledIndicatorColor = Color(0xffF4F4F4),
            unfocusedIndicatorColor = Color(0xffF4F4F4),
        ),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        value = currentName,
        onValueChange = {
            currentName = it
            onTextChanged(currentName)
        }
    )
}

@Composable
fun ProfileEditConfirmButton(onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(47.dp))
    Button(
        modifier = Modifier
            .padding(horizontal = 17.dp, vertical = 5.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff00FF87)),
        onClick = { onClick() },
    ) {
        Text(text = "تایید", style = normal600Size16TextStyle, color = Color(0xff3D195B))
    }
    Spacer(modifier = Modifier.height(50.dp))
}

