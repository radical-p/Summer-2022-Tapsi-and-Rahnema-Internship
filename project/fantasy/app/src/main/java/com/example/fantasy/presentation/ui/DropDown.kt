package com.example.fantasy.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MyTeamDropdownDemo(isOpen : MutableState<Boolean>, navController: NavController) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("نقل و انتقالات", "آخرین رویدادها", "پروفایل")
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
                    isOpen.value = false },
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
