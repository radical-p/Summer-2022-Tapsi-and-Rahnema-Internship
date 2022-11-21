package com.example.fantasy.presentation.ui

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fantasy.R
import com.example.fantasy.presentation.viewModels.ManagerFeedViewModel
import com.example.fantasy.presentation.viewModels.ManagerFollowersViewModel
import com.example.fantasy.presentation.viewModels.ManagerFollowingsViewModel
import com.example.fantasy.presentation.viewModels.SearchManagerViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun FeedPage(navController: NavController) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
        val interactionSource1 = remember { MutableInteractionSource() }

        val managerFeedViewModel = getViewModel<ManagerFeedViewModel>()
        val managerFeed = managerFeedViewModel.managerFeed.collectAsState().value

        val managerFollowersViewModel = getViewModel<ManagerFollowersViewModel>()
        val managerFollowers = managerFollowersViewModel.managerFollowers.collectAsState().value

        val managerFollowingsViewModel = getViewModel<ManagerFollowingsViewModel>()
        val managerFollowings = managerFollowingsViewModel.managerFollowings.collectAsState().value

        val searchManagerViewModel = getViewModel<SearchManagerViewModel>()
        val searchResult = searchManagerViewModel.managersList.collectAsState().value

        Column(Modifier.background(Color(0xffFFFFFF)).clickable(
            interactionSource = interactionSource1,
            indication = null,
        ) {
            keyboardController?.hide()
            focusManager.clearFocus(true)
        },

        ) {
            Header()
            Spacer(modifier = Modifier.height(16.dp))
            FeedPageNavBar(navController)
            Spacer(modifier = Modifier.height(15.dp))
            FeedPageSearchbar() {
                searchManagerViewModel.searchManager(it)
            }
            Spacer(modifier = Modifier.height(22.dp))

            var state by remember {
                mutableStateOf("feed")
            }
            FeedPageSelection {
                state = it
                when(it) {
                    "feed" -> {
                        managerFeedViewModel.getManagerFeed()
                    }
                    "followers" -> {
                        managerFollowersViewModel.getManagerFollowers()
                    }
                    "followings" -> {
                        managerFollowingsViewModel.getManagerFollowings()
                    }
                }
            }
            AnimatedContent(
                targetState = state,
                transitionSpec = {
                    slideInHorizontally() + fadeIn() with slideOutHorizontally() + fadeOut()
                }
            ) {
                when(state) {
                    "feed" -> {
                        Events(managerFeed)
                    }
                    "followers" -> {
                        Followers(managerFollowers) {
                            managerFollowersViewModel.searchManagerFollowers(it)
                        }
                    }
                    "followings" -> {
                        Following(managerFollowings)
                    }
                }
            }
        }
    }
}

@Composable
fun FeedPageNavBar(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .shadow(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(40.dp)
            .fillMaxSize()
            .background(Color(0xffFFFFFF))
    ) {
        val isDroppedDown = remember {
            mutableStateOf(false)
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
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
                FeedDropdownDemo(isDroppedDown, navController)
            }
            Text(
                text = "رویدادها",
                fontSize = 17.sp,
                color = Color(0xff18DEEA),
                style = normal800Size17TextStyle
            )
        }
    }
}

@Composable
fun FeedDropdownDemo(isDroppedDown: MutableState<Boolean>, navController: NavController) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("نقل و انتقالات", "تیم من", "پروفایل")
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
                            navController.navigate(Screen.Home.route)
                        }
                        2 -> {
                            navController.navigate(Screen.Profile.route)
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
fun FeedPageSearchbar(onTextChanged : (String) -> Unit) {
    Row(
        Modifier
            .padding(start = 12.dp, end = 17.dp)
    ) {
        val text = remember {
            mutableStateOf("")
        }
        TextField(colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xffFFFFFF),
            focusedIndicatorColor = Color(0xff000000),
            focusedLabelColor = Color(0xff000000),
            cursorColor = Color(0xff000000)
        ),
            trailingIcon = {
            },
            textStyle = TextStyle(textDirection = TextDirection.Rtl),
            value = text.value,
            onValueChange = {
                text.value = it
                onTextChanged(it)
            },
            label = {
                val style = TextStyle(textDirection = TextDirection.Rtl).merge(normal300Size14TextStyle)
                Row() {
                    Icon(painter = painterResource(R.drawable.searchbarlogo), contentDescription = null)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(modifier = Modifier.fillMaxWidth(), text = "اسم دوستات رو جستجو کن و دنبالشون کن", style = style)
                }
            })
    }
}

@Composable
fun FeedPageSelection(onSelectionChanged: (String) -> Unit) {
    var selectedButton by remember {
        mutableStateOf(UserSelection.Feed)
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xffF7F7F7))
            .padding(horizontal = 5.dp, vertical = 3.dp)
            .height(40.dp)
    ) {
        FeedPageSelectionButton(layoutName = "آخرین رویدادها", isSelected = UserSelection.Feed == selectedButton) {
            selectedButton = UserSelection.Feed
            onSelectionChanged("feed")
        }
        FeedPageSelectionButton(layoutName = "دنبال کنندگان", isSelected = UserSelection.Followers == selectedButton) {
            selectedButton = UserSelection.Followers
            onSelectionChanged("followers")
        }
        FeedPageSelectionButton(layoutName = "دنبال شوندگان", isSelected = UserSelection.Followings == selectedButton) {
            selectedButton = UserSelection.Followings
            onSelectionChanged("followings")
        }
    }
}

@Composable
fun RowScope.FeedPageSelectionButton(layoutName: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(onClick = { onClick() }, colors = ButtonDefaults.buttonColors(backgroundColor =
    if(isSelected) Color.White else Color(0xffF7F7F7)),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp),
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
    ) {
        Text(text = layoutName, color =  Color(0xff3D195B),
            style = if(isSelected) normal700Size14TextStyle else normal400Size14TextStyle
        )
    }
}

enum class UserSelection() {
    Followers,
    Followings,
    Feed,
}