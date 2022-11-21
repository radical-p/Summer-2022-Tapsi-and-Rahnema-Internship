package com.example.fantasy.presentation.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.presentation.viewModels.DashboardViewModel
import com.example.fantasy.presentation.viewModels.DeferredData
import com.example.fantasy.presentation.viewModels.PlayerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun FirstPageInitial(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState =  rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val dashboardViewModel = getViewModel<DashboardViewModel>()
    val dashboard = dashboardViewModel.dashboard.collectAsState().value
    val playerViewModel = getViewModel<PlayerViewModel>()
    val sidebarState = playerViewModel.sidebarState.collectAsState().value

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                PlayerViewSidebar(dashboard, dashboardViewModel, sidebarState, playerViewModel)
            },
            drawerBackgroundColor = Color.Transparent,
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContentColor = Color.Transparent,
            drawerElevation = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.BottomCenter
            ) {
                FirstPage(navController ,coroutineScope, scaffoldState, dashboardViewModel, dashboard) { filter ->
                    playerViewModel.onFilterChange(filter)
                }
            }
        }
    }
}

@Composable
fun FirstPage(
    navController: NavController,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    dashboardViewModel: DashboardViewModel,
    dashboard: DeferredData<Dashboard>,
    onFilterChanged: (Int) -> Unit
) {
    @OptIn(ExperimentalAnimationApi::class)
    (Box {
        Column {
            var schematicSelected by remember {
                mutableStateOf(true)
            }
            var isDrawerShown by remember {
                mutableStateOf(false)
            }
            Header()
            Spacer(modifier = Modifier.height(16.dp))
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                MyTeamNavBar(navController)
            }
            Spacer(modifier = Modifier.height(16.dp))
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                DateAndWeek()
            }
            SelectLayoutComponent(schematicSelected) {
                schematicSelected = it
            }
            PlayerAndMoneyRemaining(dashboard)
            AnimatedContent(
                targetState = schematicSelected,
                transitionSpec = {
                    slideInHorizontally() + fadeIn() with slideOutHorizontally() + fadeOut()
                }
            ) { isPitch ->
                if (isPitch) {
                    Pitch(dashboardViewModel, dashboard, isDrawerShown) {
                        isDrawerShown = it.isDrawerShown
                        onFilterChanged(it.filter)
                        if (isDrawerShown) {
                            coroutineScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    }
                } else {
                    ListView(dashboardViewModel, dashboard, isDrawerShown) {
                        isDrawerShown = it.isDrawerShown
                        onFilterChanged(it.filter)
                        if (isDrawerShown) {
                            coroutineScope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    }
                }
            }
        }
    })
}