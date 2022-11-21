package com.example.fantasy.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantasy.domain.repository.ManagerProfileInfo
import com.example.fantasy.domain.repository.ManagerProfileRequest
import com.example.fantasy.domain.usecase.GetManagerProfileUseCase
import com.example.fantasy.domain.usecase.UpdateManagerProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ManagerProfileViewModel(
    private val getManagerProfileUseCase: GetManagerProfileUseCase,
    private val updateManagerProfileUseCase: UpdateManagerProfileUseCase,
): ViewModel() {
    private val _managerProfile = MutableStateFlow<DeferredData<ManagerProfileInfo>>(DeferredData.Loading)
    val managerProfile: StateFlow<DeferredData<ManagerProfileInfo>> = _managerProfile

    init {
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            _managerProfile.emit(DeferredData.Loading)
            kotlin.runCatching {
                getManagerProfileUseCase.execute()
            }.onSuccess {
                _managerProfile.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managerProfile.emit(DeferredData.Error(it.toString()))
            }
        }
    }

    fun updateProfile(managerProfileRequest: ManagerProfileRequest) {
        viewModelScope.launch {
            _managerProfile.emit(DeferredData.Loading)
            kotlin.runCatching {
                updateManagerProfileUseCase.execute(managerProfileRequest)
            }.onSuccess {
                _managerProfile.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managerProfile.emit(DeferredData.Error(it.toString()))
            }
        }
    }
}