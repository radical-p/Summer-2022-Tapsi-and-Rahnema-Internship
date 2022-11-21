package com.example.fantasy.presentation.uiStates

import com.example.fantasy.domain.repository.Player
import com.example.fantasy.presentation.viewModels.DeferredData

data class SidebarState(
    val pageContent: DeferredData<PageContent>,
    var currentFilter: Int = 0,
)

data class PageContent(
    var currentPage: Int = 1,
    val pageLimit: Int = 10,
    var playersInfo: List<Player>,
    var totalPlayers: Int = 0,
)