package com.example.fantasy.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.domain.repository.Player
import com.example.fantasy.presentation.viewModels.DashboardViewModel
import com.example.fantasy.presentation.viewModels.DeferredData

@Composable
fun ListView(
    dashboardViewModel: DashboardViewModel,
    dashboard: DeferredData<Dashboard>,
    isDrawerShown: Boolean,
    onClick: (DrawerState) -> Unit
) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 26.dp, end = 26.dp)
            .fillMaxSize()) {
        HeaderListView()
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        when(dashboard) {
            is DeferredData.Error -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 270.dp)) {
                    Button(
                        modifier = Modifier.offset(y = (-140).dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff3D195B)),
                        onClick = {
                        dashboardViewModel.getDashboard()
                    }) {
                        Text(text = "تلاش مجدد", color = Color(0XffFFFFFF))
                    }
                }
            }
            is DeferredData.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 270.dp)) {
                    CircularProgressIndicator(color = Color(0xff3D195B), modifier = Modifier.offset(y = (-140).dp))
                }
            }
            is DeferredData.Loaded -> {
                GKListView(
                    dashboard.data.team.elementAt(0),
                    dashboard.data.team.elementAt(11),
                    dashboardViewModel,
                    dashboard,
                ) {
                    onClick(it)
                }
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                DEFListView(
                    dashboard.data.team.elementAt(1),
                    dashboard.data.team.elementAt(2),
                    dashboard.data.team.elementAt(3),
                    dashboard.data.team.elementAt(4),
                    dashboard.data.team.elementAt(12),
                    dashboardViewModel,
                    dashboard,
                ) {
                    onClick(it)
                }
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                MIDListView(
                    dashboard.data.team.elementAt(5),
                    dashboard.data.team.elementAt(6),
                    dashboard.data.team.elementAt(7),
                    dashboard.data.team.elementAt(8),
                    dashboard.data.team.elementAt(13),
                    dashboardViewModel,
                    dashboard,
                ) {
                    onClick(it)
                }
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                ATTListView(
                    dashboard.data.team.elementAt(9),
                    dashboard.data.team.elementAt(10),
                    dashboard.data.team.elementAt(14),
                    dashboardViewModel,
                    dashboard,
                ) {
                    onClick(it)
                }
            }
        }

    }
}

@Composable
fun HeaderListView() {
    Row() {
        Spacer(modifier = Modifier.weight(5f))
        Text(text = "عملکرد", style = normal300Size12TextStyle, color = Color(0xff999999), modifier = Modifier.weight(2f))
        Text(text = "قیمت", style = normal300Size12TextStyle, color = Color(0xff999999), modifier = Modifier.weight(0.7f))
    }
    Divider(thickness = 1.dp)
}

@Composable
fun GKListView(
    GK1: Player,
    GK2: Player,
    dashboardViewModel: DashboardViewModel,
    dashboard: DeferredData<Dashboard>,
    onClick: (DrawerState) -> Unit
) {
    Column() {
        val style = TextStyle(textDirection = TextDirection.Rtl).merge(normal800Size14TextStyle)
        Row() {
            Text(text = "دروازه بانان",
                Modifier
                    .weight(4f)
                    .background(Color(0xff3D195B))
                    .padding(end = 4.dp)
                    .fillMaxSize(), style = style, color = Color(0xff00FF87)
            )
            Spacer(
                Modifier
                    .weight(6f)
            )
        }
        ListViewPlayerInfo(player = GK1) {
            sendRequest(it, dashboard, dashboardViewModel, 0) {
                onClick(DrawerState(1, true))
            }
        }
        ListViewPlayerInfo(player = GK2) {
            sendRequest(it, dashboard, dashboardViewModel, 11) {
                onClick(DrawerState(1, true))
            }
        }
    }
}

@Composable
fun DEFListView(
    DEF1: Player,
    DEF2: Player,
    DEF3: Player,
    DEF4: Player,
    DEF5: Player,
    dashboardViewModel: DashboardViewModel,
    dashboard: DeferredData<Dashboard>,
    onClick: (DrawerState) -> Unit
) {
    Column() {
        val style = TextStyle(textDirection = TextDirection.Rtl).merge(normal800Size14TextStyle)
        Row() {
            Text(text = "مدافعان",
                Modifier
                    .weight(4f)
                    .background(Color(0xff3D195B))
                    .padding(end = 4.dp)
                    .fillMaxSize(), style = style, color = Color(0xff00FF87)
            )
            Spacer(
                Modifier
                    .weight(6f)
            )
        }
        ListViewPlayerInfo(player = DEF1) {
            sendRequest(it, dashboard, dashboardViewModel, 1) {
                onClick(DrawerState(2, true))
            }
        }
        ListViewPlayerInfo(player = DEF2) {
            sendRequest(it, dashboard, dashboardViewModel, 2) {
                onClick(DrawerState(2, true))
            }
        }
        ListViewPlayerInfo(player = DEF3) {
            sendRequest(it, dashboard, dashboardViewModel, 3) {
                onClick(DrawerState(2, true))
            }
        }
        ListViewPlayerInfo(player = DEF4) {
            sendRequest(it, dashboard, dashboardViewModel, 4) {
                onClick(DrawerState(2, true))
            }
        }
        ListViewPlayerInfo(player = DEF5) {
            sendRequest(it, dashboard, dashboardViewModel, 12) {
                onClick(DrawerState(2, true))
            }
        }
    }
}

@Composable
fun MIDListView(
    MID1: Player,
    MID2: Player,
    MID3: Player,
    MID4: Player,
    MID5: Player,
    dashboardViewModel: DashboardViewModel,
    dashboard: DeferredData<Dashboard>,
    onClick: (DrawerState) -> Unit
) {
    Column() {
        val style = TextStyle(textDirection = TextDirection.Rtl).merge(normal800Size14TextStyle)
        Row() {
            Text(text = "هافبک ها",
                Modifier
                    .weight(4f)
                    .background(Color(0xff3D195B))
                    .padding(end = 4.dp)
                    .fillMaxSize(), style = style, color = Color(0xff00FF87)
            )
            Spacer(
                Modifier
                    .weight(6f)
            )
        }
        ListViewPlayerInfo(player = MID1) {
            sendRequest(it, dashboard, dashboardViewModel, 5) {
                onClick(DrawerState(3, true))
            }
        }
        ListViewPlayerInfo(player = MID2) {
            sendRequest(it, dashboard, dashboardViewModel, 6) {
                onClick(DrawerState(3, true))
            }
        }
        ListViewPlayerInfo(player = MID3) {
            sendRequest(it, dashboard, dashboardViewModel, 7) {
                onClick(DrawerState(3, true))
            }
        }
        ListViewPlayerInfo(player = MID4) {
            sendRequest(it, dashboard, dashboardViewModel, 8) {
                onClick(DrawerState(3, true))
            }
        }
        ListViewPlayerInfo(player = MID5) {
            sendRequest(it, dashboard, dashboardViewModel, 13) {
                onClick(DrawerState(3, true))
            }
        }
    }
}

@Composable
fun ATTListView(
    ATT1: Player,
    ATT2: Player,
    ATT3: Player,
    dashboardViewModel: DashboardViewModel,
    dashboard: DeferredData<Dashboard>,
    onClick: (DrawerState) -> Unit
) {
    Column() {
        val style = TextStyle(textDirection = TextDirection.Rtl).merge(normal800Size14TextStyle)
        Row() {
            Text(text = "مهاجمین",
                Modifier
                    .weight(4f)
                    .background(Color(0xff3D195B))
                    .padding(end = 4.dp)
                    .fillMaxSize(), style = style, color = Color(0xff00FF87)
            )
            Spacer(
                Modifier
                    .weight(6f)
            )
        }
        ListViewPlayerInfo(player = ATT1) {
            sendRequest(it, dashboard, dashboardViewModel, 9) {
                onClick(DrawerState(4, true))
            }
        }
        ListViewPlayerInfo(player = ATT2) {
            sendRequest(it, dashboard, dashboardViewModel, 10) {
                onClick(DrawerState(4, true))
            }
        }
        ListViewPlayerInfo(player = ATT3) {
            sendRequest(it, dashboard, dashboardViewModel, 14) {
                onClick(DrawerState(4, true))
            }
        }
    }
}

@Composable
fun ListViewPlayerInfo(player: Player, onClick: (Boolean) -> Unit) {
    Row(
        Modifier
            .clickable { onClick(player.isInTeam) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .weight(3f),
        ) {
            Text(modifier = Modifier
                .fillMaxSize()
                .padding(4.dp), textAlign = TextAlign.Start, text = player.name, style = normal500Size12TextStyle, color = Color(0xff3D195B))
        }
        Spacer(modifier = Modifier.weight(6f))
        Box(
            Modifier
                .weight(3f),
        ) {
            Text(text = player.rating, textAlign = TextAlign.Start, style = normal500Size12TextStyle, color = Color(0xff3D195B))
        }
        Box(
            Modifier
                .weight(1.5f)
                .padding(horizontal = 7.dp),
        ) {
            Text(text = player.price, textAlign = TextAlign.Start, style = normal500Size12TextStyle, color = Color(0xff3D195B))
        }
    }
    Divider(thickness = 1.dp)
}

private fun sendRequest(
    it: Boolean,
    dashboard: DeferredData<Dashboard>,
    dashboardViewModel: DashboardViewModel,
    index: Int,
    onClick: (Boolean) -> Unit,
) {
    if(it) {
        dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(index)!!.id, index)
    } else {
        dashboard.data?.currentPlayerIndex = index
        onClick(true)
    }
}

data class DrawerState(
    val filter: Int,
    val isDrawerShown: Boolean
)