package com.example.fantasy.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.domain.usecase.AddToTeamUseCase
import com.example.fantasy.domain.usecase.GetDashboardUseCase
import com.example.fantasy.domain.usecase.RemoveFromTeamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val getDashboardUseCase: GetDashboardUseCase,
    private val removeFromTeamUseCase: RemoveFromTeamUseCase,
    private val addToTeamUseCase: AddToTeamUseCase,
    ): ViewModel() {
    private val _dashboard = MutableStateFlow<DeferredData<Dashboard>>(DeferredData.Loading)
    val dashboard: StateFlow<DeferredData<Dashboard>> = _dashboard

    init {
        viewModelScope.launch {
            _dashboard.emit(DeferredData.Loading)
            kotlin.runCatching {
                getDashboardUseCase.execute()
            }.onSuccess {
                _dashboard.emit(DeferredData.Loaded(it))
            }.onFailure {
                _dashboard.emit(DeferredData.Error(""))
            }
        }
    }

    fun getDashboard() {
        viewModelScope.launch {
            _dashboard.emit(DeferredData.Loading)
            kotlin.runCatching {
                getDashboardUseCase.execute()
            }.onSuccess {
                _dashboard.emit(DeferredData.Loaded(it))
            }.onFailure {
                _dashboard.emit(DeferredData.Error(""))
            }
        }
    }

    fun addToTeam(id: String, index: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                addToTeamUseCase.execute(id, index)
            }.onSuccess {
                getDashboard()
            }.onFailure {
                getDashboard()
            }
        }
    }

    fun removeFromTeam(id: String, index: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                removeFromTeamUseCase.execute(id, index)
            }.onSuccess {
                getDashboard()
            }.onFailure {
                getDashboard()
            }
        }
    }
}