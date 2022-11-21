package com.example.fantasy.presentation.viewModels


import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantasy.domain.repository.Player
import com.example.fantasy.domain.usecase.GetPlayersListUseCase
import com.example.fantasy.domain.usecase.SearchPlayersUseCase
import com.example.fantasy.presentation.uiStates.PageContent
import com.example.fantasy.presentation.uiStates.SidebarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val getPlayersListUseCase: GetPlayersListUseCase,
    private val searchPlayersUseCase: SearchPlayersUseCase
) : ViewModel() {

    private fun getPlayers(filter: Int) {
        sidebarState.value.currentFilter = filter
        val pageLimit = sidebarState.value.pageContent.data?.pageLimit?: 10
        val page = sidebarState.value.pageContent.data?.currentPage?: 1
        var playersList2 = sidebarState.value.pageContent.data?.playersInfo?: emptyList()

        viewModelScope.launch {
            _sidebarState.emit(SidebarState(DeferredData.Loading, sidebarState.value.currentFilter))
            kotlin.runCatching {
                getPlayersListUseCase.execute(filter, pageLimit, page)
            }.onSuccess {
                val playersList = mutableListOf<Player>()
                playersList.addAll(playersList2.toTypedArray())
                playersList.addAll(it.playersList)
                playersList2 = playersList
                _sidebarState.emit(SidebarState(DeferredData.Loaded(PageContent(page, pageLimit, playersList2, it.total)), filter))
            }.onFailure {
                _sidebarState.emit(SidebarState(DeferredData.Error(it.toString()), sidebarState.value.currentFilter))
            }
        }
    }

    private val _sidebarState = MutableStateFlow(SidebarState(DeferredData.Loading, currentFilter = 0))
    val sidebarState: StateFlow<SidebarState> = _sidebarState

    fun onTextChanged(it: String, filter: Int) {
        sidebarState.value.currentFilter = filter
        val pageLimit = sidebarState.value.pageContent.data?.pageLimit?: 10
        val page = sidebarState.value.pageContent.data?.currentPage?: 1
        var playersList2 = emptyList<Player>()

        viewModelScope.launch {
            _sidebarState.emit(SidebarState(DeferredData.Loading, sidebarState.value.currentFilter))
            kotlin.runCatching {
                searchPlayersUseCase.execute(filter, pageLimit, page, it)
            }.onSuccess {
                val playersList = mutableListOf<Player>()
                playersList.addAll(playersList2.toTypedArray())
                playersList.addAll(it.playersList)
                playersList2 = playersList
                _sidebarState.emit(SidebarState(DeferredData.Loaded(PageContent(page, pageLimit, playersList2, it.total)), filter))
            }.onFailure {
                _sidebarState.emit(SidebarState(DeferredData.Error(it.toString()), sidebarState.value.currentFilter))
            }
        }
    }

    fun onFilterChange(filter: Int) {
        _sidebarState.value.pageContent.data?.currentPage = 1
        sidebarState.value.pageContent.data?.playersInfo = emptyList()
        getPlayers(filter = filter)
    }

    fun loadMore(filter: Int) {
        getPlayers(filter = filter)
    }

    fun searchMore(filter: Int, it: String) {
        sidebarState.value.currentFilter = filter
        val pageLimit = sidebarState.value.pageContent.data?.pageLimit?: 10
        val page = sidebarState.value.pageContent.data?.currentPage?: 1
        var playersList2 = sidebarState.value.pageContent.data?.playersInfo?: emptyList()

        viewModelScope.launch {
            _sidebarState.emit(SidebarState(DeferredData.Loading, sidebarState.value.currentFilter))
            kotlin.runCatching {
                searchPlayersUseCase.execute(filter, pageLimit, page, it)
            }.onSuccess {
                val playersList = mutableListOf<Player>()
                playersList.addAll(playersList2.toTypedArray())
                playersList.addAll(it.playersList)
                playersList2 = playersList
                _sidebarState.emit(SidebarState(DeferredData.Loaded(PageContent(page, pageLimit, playersList2, it.total)), filter))
            }.onFailure {
                _sidebarState.emit(SidebarState(DeferredData.Error(it.toString()), sidebarState.value.currentFilter))
            }
        }
    }

//    init {
//        getPlayers(filter = 0)
//    }
}

@Stable
sealed class DeferredData<out E> {
    abstract val data: E?

    @Stable
    object Loading : DeferredData<Nothing>() {
        override val data: Nothing?
            get() = null
    }

    @Stable
    data class Error(val title: String) : DeferredData<Nothing>() {
        override val data: Nothing?
            get() = null
    }

    @Stable
    data class Loaded<E>(override val data: E) : DeferredData<E>()
}
