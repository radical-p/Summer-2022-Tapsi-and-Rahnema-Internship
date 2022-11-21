package com.example.fantasy.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.fantasy.domain.repository.ManagerFollowing
import com.example.fantasy.domain.repository.ManagerFollowings
import com.example.fantasy.presentation.viewModels.DeferredData
import com.example.fantasy.presentation.viewModels.ManagerFollowingsViewModel


@Composable
fun Following(managerFollowings: DeferredData<ManagerFollowings>) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
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

            }

            when(managerFollowings) {
                is DeferredData.Error -> {
                    Box(modifier = Modifier.fillMaxSize().background(Color(0xffFFFFFF)))
                }
                is DeferredData.Loading -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(color = Color(0xff05F1FF))
                    }
                }
                is DeferredData.Loaded -> {
                    Box(modifier = Modifier.fillMaxSize().background(Color(0xffFFFFFF))) {
                        LazyColumn {
                            items(items = managerFollowings.data.managerFollowings) {
                                ManagerFollowingsList(
                                    isFollowed = true,
                                    playerName = it.fullName,
                                    managerId = it.managerId,
                                    image = it.managerImage,
                                ) {

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}