package com.example.fantasy.presentation.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantasy.domain.repository.DateAndWeek
import com.example.fantasy.domain.usecase.GetDateAndWeekUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DateAndWeekViewModel(private val getDateAndWeekUseCase: GetDateAndWeekUseCase): ViewModel() {
    private val _dateandweek = MutableStateFlow<DeferredData<DateAndWeek>>(DeferredData.Loading)
    val dateandweek: StateFlow<DeferredData<DateAndWeek>> = _dateandweek

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                getDateAndWeekUseCase.execute()
            }.onSuccess {
                _dateandweek.emit(DeferredData.Loaded(it))
            }.onFailure {
                _dateandweek.emit(DeferredData.Error(""))
            }
        }
    }

    fun getDateAndWeek() {
        viewModelScope.launch {
            kotlin.runCatching {
                getDateAndWeekUseCase.execute()
            }.onSuccess {
                _dateandweek.emit(DeferredData.Loaded(it))
            }.onFailure {
                _dateandweek.emit(DeferredData.Error(""))
            }
        }
    }

}