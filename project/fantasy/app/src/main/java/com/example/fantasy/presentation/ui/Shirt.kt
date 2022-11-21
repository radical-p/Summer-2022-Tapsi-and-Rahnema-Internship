package com.example.fantasy.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fantasy.R
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.domain.repository.Player
import com.example.fantasy.presentation.viewModels.DashboardViewModel
import com.example.fantasy.presentation.viewModels.DeferredData

val mid = 8.dp
val att = 26.dp
val gk = 15.dp

@Composable
fun Shirt(
    dashboardViewModel: DashboardViewModel,
    dashboard: DeferredData<Dashboard>,
    onClick: (DrawerState) -> Unit
) {
    Column() {
        Spacer(modifier = Modifier.weight(0.03f))
        //GK
        Row(
            modifier = Modifier
                .weight(0.20f)
                .padding(start = 100.dp, end = 100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PlayerShirt(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(start = gk, end = gk)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(0)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(0)!!.id, 0)
                } else {
                    dashboard.data?.currentPlayerIndex = 0
                    onClick(DrawerState(1, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(start = gk, end = gk)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(11)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(11)!!.id, 11)
                } else {
                    dashboard.data?.currentPlayerIndex = 11
                    onClick(DrawerState(1, true))
                }
            }
        }

        Spacer(modifier = Modifier.weight(0.01f))

        //DEF
        Row(
            modifier = Modifier
                .weight(0.20f)
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(1)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(1)!!.id, 1)
                } else {
                    dashboard.data?.currentPlayerIndex = 1
                    onClick(DrawerState(2, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(2)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(2)!!.id, 2)
                } else {
                    dashboard.data?.currentPlayerIndex = 2
                    onClick(DrawerState(2, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(3)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(3)!!.id, 3)
                } else {
                    dashboard.data?.currentPlayerIndex = 3
                    onClick(DrawerState(2, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(4)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(4)!!.id, 4)
                } else {
                    dashboard.data?.currentPlayerIndex = 4
                    onClick(DrawerState(2, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(12)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(12)!!.id, 12)
                } else {
                    dashboard.data?.currentPlayerIndex = 12
                    onClick(DrawerState(2, true))
                }
            }
        }

        Spacer(modifier = Modifier.weight(0.01f))

        //MID
        Row(
            modifier = Modifier
                .weight(0.20f)
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(5)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(5)!!.id, 5)
                } else {
                    dashboard.data?.currentPlayerIndex = 5
                    onClick(DrawerState(3, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(6)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(6)!!.id, 6)
                } else {
                    dashboard.data?.currentPlayerIndex = 6
                    onClick(DrawerState(3, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(7)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(7)!!.id, 7)
                } else {
                    dashboard.data?.currentPlayerIndex = 7
                    onClick(DrawerState(3, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(8)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(8)!!.id, 8)
                } else {
                    dashboard.data?.currentPlayerIndex = 8
                    onClick(DrawerState(3, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = mid, end = mid)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(13)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(13)!!.id, 13)
                } else {
                    dashboard.data?.currentPlayerIndex = 13
                    onClick(DrawerState(3, true))
                }
            }
        }

        Spacer(modifier = Modifier.weight(0.01f))

        //ATT
        Row(
            modifier = Modifier
                .weight(0.20f)
                .padding(start = 35.dp, end = 35.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = att, end = att)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(9)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(9)!!.id, 9)
                } else {
                    dashboard.data?.currentPlayerIndex = 9
                    onClick(DrawerState(4, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = att, end = att)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(10)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(10)!!.id, 10)
                } else {
                    dashboard.data?.currentPlayerIndex = 10
                    onClick(DrawerState(4, true))
                }
            }
            PlayerShirt(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = att, end = att)
                    .fillMaxSize(),
                player = dashboard.data?.team?.elementAt(14)!!,
            ) {
                if(it) {
                    dashboardViewModel.removeFromTeam(dashboard.data?.team?.elementAt(14)!!.id, 14)
                } else {
                    dashboard.data?.currentPlayerIndex = 14
                    onClick(DrawerState(4, true))
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.01f))
    }
}

@Composable
fun PlayerShirt(modifier: Modifier, player: Player, onClick: (Boolean) -> Unit) {
    Column(modifier = modifier) {
        if(player.isInTeam) {
            SingleRahnemaShirt(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxSize()
            ) { onClick(player.isInTeam) }
            InformationBox(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(start = 4.dp, end = 4.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxSize(),
                name = player.name,
                price = player.price
            )
        } else {
            SingleShirt(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxSize()
            ) { onClick(player.isInTeam) }
            InformationBox(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(start = 4.dp, end = 4.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .alpha(0f)
                    .fillMaxSize(),
                name = player.name,
                price = player.price
            )
        }
    }
}

@Composable
fun SingleShirt(modifier: Modifier, onClick: () -> Unit) {
    Image(
        modifier = modifier.clickable { onClick() },
        painter = painterResource(R.drawable.shirt),
        contentDescription = "player-shirt",
        contentScale = ContentScale.Fit
    )
}

@Composable
fun SingleRahnemaShirt(modifier: Modifier, onClick: () -> Unit) {
    Image(
        modifier = modifier.clickable { onClick() },
        painter = painterResource(R.drawable.rahnemashirt),
        contentDescription = "player-shirt",
        contentScale = ContentScale.Fit
    )
}

@Composable
fun InformationBox(modifier: Modifier, name: String, price: String) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
                .background(Color(0xff37013B))
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = name,
                fontSize = 7.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
                .background(Color(0xffCCFFE4))
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = price,
                fontSize = 7.sp,
                textAlign = TextAlign.Center
            )
        }
    }

}