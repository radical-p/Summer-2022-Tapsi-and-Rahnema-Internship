package com.example.fantasy.presentation.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.LayoutDirection.*
import androidx.compose.ui.unit.dp
import com.example.fantasy.domain.repository.ManagerFollowers
import com.example.fantasy.presentation.viewModels.DeferredData
import com.example.fantasy.presentation.viewModels.FollowManagerViewModel
import com.example.fantasy.presentation.viewModels.SearchManagerViewModel
//import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.getViewModel

@Composable
fun Followers(managerFollowers: DeferredData<ManagerFollowers>, onTextChanged: (String) -> Unit) {

    val followManagerViewModel = getViewModel<FollowManagerViewModel>()

    CompositionLocalProvider(LocalLayoutDirection provides Rtl) {
        Column(
            Modifier
                .background(Color(0XffFFFFFF))
                .padding(vertical = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
        ) {

            var currentText by remember {
                mutableStateOf("")
            }

            FollowerSearchbar() {
                onTextChanged(it)
            }

            when(managerFollowers) {
                is DeferredData.Loading -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(color = Color(0xff05F1FF))
                    }
                }
                is DeferredData.Error -> {
                    Box(modifier = Modifier.fillMaxSize().background(Color(0xffFFFFFF)))
                }
                is DeferredData.Loaded -> {
                    Box(modifier = Modifier.fillMaxSize().background(Color(0xffFFFFFF))) {
                        LazyColumn {
                            items(items = managerFollowers.data.managerFollowers) {
                                ManagerFollowingsList(
                                    isFollowed = it.isFollowed,
                                    playerName = it.fullName,
                                    managerId = it.managerId,
                                    image = it.managerImage,
                                ) { managerId ->
                                    followManagerViewModel.followManager(managerId = managerId)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FollowerSearchbar(onTextChanged: (String) -> Unit) {
    Row(
        Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        val text = remember {
            mutableStateOf("")
        }
        TextField(colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xffFFFFFF)),
            trailingIcon = {
            },
            textStyle = TextStyle(textDirection = TextDirection.Rtl),
            value = text.value,
            onValueChange = {
                text.value = it
                onTextChanged(it)
            },
            label = {
                val style =
                    TextStyle(textDirection = TextDirection.Rtl).merge(normal300Size14TextStyle)
                Row() {
                    Icon(
                        painter = painterResource(com.example.fantasy.R.drawable.searchbarlogo),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(modifier = Modifier.fillMaxWidth(), text = "جستجو", style = style)
                }
            })
    }
    Spacer(Modifier.height(19.dp))
}

@Composable
fun ManagerFollowingsList(isFollowed: Boolean, playerName: String, managerId: String, image: String?, onClick: (String) -> Unit) {
    val style =
        TextStyle(textDirection = TextDirection.Rtl).merge(normal300Size14TextStyle)
    Row {
        Spacer(modifier = Modifier.width(10.dp))
//        GlideImage(
//            imageModel = image,
//            modifier = Modifier
//                .height(60.dp)
//                .width(60.dp)
//                .clip(CircleShape),
//            loading = {
//                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
//            },
//            failure = {
//                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
//            }
//        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 15.dp),
            text = playerName, style = style, textAlign = TextAlign.Start
        )
        if (isFollowed) {
            FollowedButton()
        } else {
            NotFollowedButton() {
                onClick(managerId)
            }
        }
    }
}

@Composable
fun FollowedButton() {
    Box(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .background(Color.White),
        Alignment.CenterEnd) {
        Button(
            onClick = {  },
            modifier = Modifier
                .height(50.dp)
                .width(100.dp)
                .padding(vertical = 7.dp, horizontal = 5.dp)
                .background(Color.White),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(20)
        ) {
            Text(
                modifier =
                Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                text = "مشاهده", color = Color.Black
            )
        }
    }
}

@Composable
fun NotFollowedButton(onClick: () -> Unit) {
    Box(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .background(Color.White),
        Alignment.CenterEnd) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .height(50.dp)
                .width(100.dp)
                .padding(vertical = 7.dp, horizontal = 5.dp)
                .background(Color.White),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color(0xff05F1FF)),
            shape = RoundedCornerShape(20)
        ) {
            Text(
                modifier =
                Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                text = "دنبال کردن", color = Color(0xff05F1FF)
            )
        }
    }
}