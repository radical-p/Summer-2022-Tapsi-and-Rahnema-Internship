package com.example.fantasy.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.domain.usecase.GetDashboardUseCase
import com.example.fantasy.domain.usecase.SubstitutionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubstitutionViewModel(
    private val substitutionUseCase: SubstitutionUseCase,
    private val getDashboardUseCase: GetDashboardUseCase,
): ViewModel() {
    private val _team = MutableStateFlow<DeferredData<Dashboard>>(DeferredData.Loading)
    val team: StateFlow<DeferredData<Dashboard>> = _team

    init {
        getTeam()
    }

    private fun getTeam() {
        viewModelScope.launch {
            _team.emit(DeferredData.Loading)
            kotlin.runCatching {
                getDashboardUseCase.execute()
            }.onSuccess {
                _team.emit(DeferredData.Loaded(it))
            }.onFailure {
                _team.emit(DeferredData.Error(it.toString()))
            }
        }
    }

    fun substitutePlayer(inPlayer: Int, outPlayer: Int) {
        viewModelScope.launch {
            _team.emit(DeferredData.Loading)
            kotlin.runCatching {
                substitutionUseCase.execute(inPlayer, outPlayer)
            }.onSuccess {
                getTeam()
            }.onFailure {
                getTeam()
            }
        }
    }
}