package com.example.fantasy.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fantasy.R
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.presentation.viewModels.DashboardViewModel
import com.example.fantasy.presentation.viewModels.DeferredData

@Composable
fun Pitch(
    dashboardViewModel: DashboardViewModel,
    dashboard: DeferredData<Dashboard>,
    isDrawerShown: Boolean,
    onClick: (DrawerState) -> Unit
) {
    Box {
        PitchBackground()
        when(dashboard) {
            is DeferredData.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    PitchBackground()
                    CircularProgressIndicator(color = Color(0xff3D195B))
                }
            }
            is DeferredData.Loaded -> {
                Shirt(dashboardViewModel, dashboard) {
                    onClick(it)
                }
            }
            is DeferredData.Error -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    PitchBackground()
                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff3D195B)), onClick = {
                        dashboardViewModel.getDashboard()
                    }) {
                        Text(text = "تلاش مجدد", color = Color(0XffFFFFFF))
                    }
                }
            }
        }
    }
}

@Composable
fun PitchBackground() {
    Image(
        modifier = Modifier
            .padding(start = 26.dp, end = 26.dp)
            .fillMaxSize(),
        painter = painterResource(R.drawable.pitch),
        contentDescription = "play ground",
        contentScale = ContentScale.FillBounds,
    )
}