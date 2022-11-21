package com.example.fantasy.presentation.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.example.fantasy.R
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.domain.repository.Player
import com.example.fantasy.presentation.uiStates.SidebarState
import com.example.fantasy.presentation.viewModels.DashboardViewModel
import com.example.fantasy.presentation.viewModels.DeferredData
import com.example.fantasy.presentation.viewModels.PlayerViewModel

@Composable
fun PlayerViewSidebar(
    dashboard: DeferredData<Dashboard>,
    dashboardViewModel: DashboardViewModel,
    sidebarState: SidebarState,
    playerViewModel: PlayerViewModel
) {
    Column(
        Modifier
            .padding(vertical = 53.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0XffFFFFFF))
            .width(273.dp)
    ) {
        var currentText by remember {
            mutableStateOf("")
        }

        PlayerViewSidebarHeader()
        PlayerViewSidebarSearchbar() {
            sidebarState.pageContent.data?.currentPage = 1
            playerViewModel.onTextChanged(it, sidebarState.currentFilter)
            currentText = it
        }
        PlayerViewSidebarAllFilters(sidebarState) {
            playerViewModel.onFilterChange(it)
        }
        when(sidebarState.pageContent) {
            is DeferredData.Loading -> {
                PlayerViewSidebarResultNumber(playerNumbers = "")
                PlayerViewSidebarLoading()
            }
            is DeferredData.Loaded -> {
                PlayerViewSidebarResultNumber(playerNumbers = sidebarState.pageContent.data.totalPlayers.toString())
                val playersInfo = sidebarState.pageContent.data.playersInfo
                PlayerViewSidebarPlayersInfo(sidebarState ,playersInfo, dashboard, dashboardViewModel) {
                    sidebarState.pageContent.data.currentPage += 1
                    if (currentText.isEmpty()) {
                        playerViewModel.loadMore(sidebarState.currentFilter)
                    }
                    else {
                        playerViewModel.searchMore(sidebarState.currentFilter, currentText)
                    }
                }
            }
            is DeferredData.Error -> {
                PlayerViewSidebarResultNumber(playerNumbers = "0")
                PlayerViewSidebarError()
            }
        }
    }
}

@Composable
fun PlayerViewSidebarLoading() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(color = Color(0xff00FF87))
    }
}

@Composable
fun PlayerViewSidebarError() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "نتیجه ای یافت نشد.")
    }
}

@Composable
fun PlayerViewSidebarHeader() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color(0xff3D195B)), contentAlignment = Alignment.Center
    ) {
        Text(text = "انتخاب بازیکن", color = Color(0xffFFFFFF), style = normal800Size18TextStyle)
    }
    Spacer(Modifier.height(22.dp))
}

@Composable
fun PlayerViewSidebarSearchbar(onTextChanged : (String) -> Unit) {
    Row(
        Modifier
            .padding(start = 12.dp, end = 17.dp)
    ) {
        val text = remember {
            mutableStateOf("")
        }
        TextField(colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(0xffFFFFFF)), trailingIcon = {
        }, textStyle = TextStyle(textDirection = TextDirection.Rtl),
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
                Text(modifier = Modifier.fillMaxWidth(), text = "جستجو", style = style)
            }
        })
    }
    Spacer(Modifier.height(19.dp))
}

@Composable
fun PlayerViewSidebarFilterButton(playerRole: String, isSelected: Boolean, onClick: () -> Unit) {
    val selectedModifier = Modifier
        .fillMaxHeight()
        .background(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xff03FBB8),
                    Color(0xff04F5EC)
                )
            )
        )
    val defaultModifier = Modifier
        .fillMaxHeight()
        .background(Color(0xffFFFFFF))
    Button(onClick = { onClick() },
        Modifier
            .height(30.dp)
            .padding(horizontal = 5.dp)
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffFFFFFF))) {
        Box(modifier = (if(isSelected) selectedModifier else defaultModifier).align(Alignment.CenterVertically), contentAlignment = Alignment.Center) {
            Text(text = playerRole, style = normal500Size12TextStyle, modifier = Modifier
                .width(40.dp)
                .padding(horizontal = 5.dp), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun PlayerViewSidebarAllFilters(
    sidebarState: SidebarState,
    onFilterChanged: (Int) -> Unit,
) {
    Row(
        Modifier
            .padding(start = 10.dp, end = 10.dp)) {

        PlayerViewSidebarFilterButton(playerRole = "All",
            isSelected = sidebarState.currentFilter == 0) {
            onFilterChanged(0)
        }
        PlayerViewSidebarFilterButton(playerRole = "GK",
            isSelected = sidebarState.currentFilter == 1) {
            println("1 filter")
            onFilterChanged(1)
        }
        PlayerViewSidebarFilterButton(playerRole = "DEF",
            isSelected = sidebarState.currentFilter == 2) {
            onFilterChanged(2)
        }
        PlayerViewSidebarFilterButton(playerRole = "MID",
            isSelected = sidebarState.currentFilter == 3) {
            onFilterChanged(3)
        }
        PlayerViewSidebarFilterButton(playerRole = "ATT",
            isSelected = sidebarState.currentFilter == 4) {
            onFilterChanged(4)
        }

    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun PlayerViewSidebarResultNumber(playerNumbers: String) {
    val style = TextStyle(textDirection = TextDirection.Rtl).merge(normal700Size11TextStyle)
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(textAlign = TextAlign.Center, text = "$playerNumbers بازیکن نمایش داده شده است", style = style, modifier = Modifier
            .width(180.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xff03FBB8),
                        Color(0xff04F5EC)
                    )
                )
            )
            .padding(vertical = 7.dp)
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun PlayerViewSidebarPlayersInfo(
    sidebarState: SidebarState,
    playersInfo: List<Player>,
    dashboard: DeferredData<Dashboard>,
    dashboardViewModel: DashboardViewModel,
    onLoadMore: () -> Unit,
) {
    Box(modifier = Modifier.height(20.dp)
    ) {
        Row() {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "نام بازیکن", style = normal300Size12TextStyle, color = Color(0xff999999), modifier = Modifier.weight(3f))

            val modifierDown = Modifier
                .size(10.dp)
                .align(Alignment.CenterVertically)
                .rotate(180f)
            val modifierUp = Modifier
                .size(10.dp)
                .align(Alignment.CenterVertically)
            Spacer(modifier = Modifier.weight(3f))
            Row(
                Modifier
                    .align(Alignment.CenterVertically)
                    .weight(3f)) {
                Text(text = "عملکرد", style = normal300Size12TextStyle, color = Color(0xff999999), modifier = Modifier
                    .clickable { })
                Image(painter = painterResource(R.drawable.polygon_3), contentDescription = null, modifier = modifierUp)
            }
            Row(
                Modifier
                    .align(Alignment.CenterVertically)
                    .weight(3f)) {
                Text(text = "قیمت", style = normal300Size12TextStyle, color = Color(0xff999999), modifier = Modifier
                    .clickable { })
                Image(painter = painterResource(R.drawable.polygon_3), contentDescription = null, modifier = modifierUp)
            }
        }
    }
    val state = rememberLazyListState()
    val shouldGetNewItems by remember {
        derivedStateOf {
            state.layoutInfo.visibleItemsInfo.lastOrNull()?.index == state.layoutInfo.totalItemsCount -1
        }
    }
    LaunchedEffect(key1 = shouldGetNewItems) {
        if (shouldGetNewItems and (sidebarState.pageContent.data?.playersInfo?.size != sidebarState.pageContent.data?.totalPlayers)) {
            onLoadMore()
        }
    }

    Box(Modifier.fillMaxHeight()) {
        LazyColumn(state = state, modifier = Modifier.fillMaxHeight()) {
            items(items = playersInfo) { it ->
                PlayerViewSidebarSinglePlayerInfo(
                    playerPrice = it.price,
                    playerRating = it.rating,
                    playerName = it.name,
                    playerTeam = it.team,
                    playerId = it.id
                ) { playerId ->
                    dashboardViewModel.addToTeam(playerId, dashboard.data?.currentPlayerIndex!!)
                }
            }
        }
    }
}

@Composable
fun PlayerViewSidebarSinglePlayerInfo(playerPrice: String, playerRating: String, playerName: String, playerTeam: String, playerId: String, onAddPlayer: (String) -> Unit) {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 17.dp)
        .height(1.dp)
        .background(Color(0xffEFEFEF))
    )
    Box(Modifier.clickable {
        onAddPlayer(playerId)
    }) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Column(Modifier.weight(3.5f), horizontalAlignment = Alignment.Start) {
                Text(text = playerName, style = normal500Size12TextStyle, color = Color(0xff3D195B))
                Text(text = playerTeam, style = normal400Size9TextStyle, color = Color(0x5B3D195B))
            }
            Box(
                Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = playerRating, style = normal700Size10TextStyle, color = Color(0xff3D195B))
            }
            Box(
                Modifier
                    .weight(2.5f)
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = playerPrice, style = normal700Size10TextStyle, color = Color(0xff3D195B))
            }
        }
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 17.dp)
        .height(1.dp)
        .background(Color(0xffEFEFEF))
    )
}