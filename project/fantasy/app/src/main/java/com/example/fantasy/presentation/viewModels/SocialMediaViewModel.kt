package com.example.fantasy.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fantasy.domain.repository.ManagerFeed
import com.example.fantasy.domain.repository.ManagerFollowers
import com.example.fantasy.domain.repository.ManagerFollowings
import com.example.fantasy.domain.repository.ManagerInfo
import com.example.fantasy.domain.usecase.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ManagerFeedViewModel(
    private val getManagerFeedUseCase: GetManagerFeedUseCase,
): ViewModel() {
    private val _managerFeed = MutableStateFlow<DeferredData<ManagerFeed>>(DeferredData.Loading)
    val managerFeed: StateFlow<DeferredData<ManagerFeed>> = _managerFeed

    fun getManagerFeed() {
        viewModelScope.launch {
            _managerFeed.emit(DeferredData.Loading)
            kotlin.runCatching {
                getManagerFeedUseCase.execute()
            }.onSuccess {
                _managerFeed.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managerFeed.emit(DeferredData.Error(it.toString()))
            }
        }
    }
}

class ManagerFollowersViewModel(
    private val getManagerFollowersUseCase: GetManagerFollowersUseCase,
    private val searchManagerFollowersUseCase: SearchManagerFollowersUseCase,
): ViewModel() {
    private val _managerFollowers = MutableStateFlow<DeferredData<ManagerFollowers>>(DeferredData.Loading)
    val managerFollowers: StateFlow<DeferredData<ManagerFollowers>> = _managerFollowers

    fun getManagerFollowers() {
        viewModelScope.launch {
            _managerFollowers.emit(DeferredData.Loading)
            kotlin.runCatching {
                getManagerFollowersUseCase.execute()
            }.onSuccess {
                _managerFollowers.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managerFollowers.emit(DeferredData.Error(it.toString()))
            }
        }
    }

    fun searchManagerFollowers(searchInput: String) {
        viewModelScope.launch {
            _managerFollowers.emit(DeferredData.Loading)
            kotlin.runCatching {
                searchManagerFollowersUseCase.execute(searchInput = searchInput)
            }.onSuccess {
                _managerFollowers.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managerFollowers.emit(DeferredData.Error(it.toString()))
            }
        }
    }
}

class ManagerFollowingsViewModel(
    private val getManagerFollowingsUseCase: GetManagerFollowingsUseCase,
    private val searchManagerFollowingsUseCase: SearchManagerFollowingsUseCase,
): ViewModel() {
    private val _managerFollowings = MutableStateFlow<DeferredData<ManagerFollowings>>(DeferredData.Loading)
    val managerFollowings: StateFlow<DeferredData<ManagerFollowings>> = _managerFollowings

    fun getManagerFollowings() {
        viewModelScope.launch {
            _managerFollowings.emit(DeferredData.Loading)
            kotlin.runCatching {
                getManagerFollowingsUseCase.execute()
            }.onSuccess {
                _managerFollowings.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managerFollowings.emit(DeferredData.Error(it.toString()))
            }
        }
    }

    fun searchManagerFollowings(searchInput: String) {
        viewModelScope.launch {
            _managerFollowings.emit(DeferredData.Loading)
            kotlin.runCatching {
                searchManagerFollowingsUseCase.execute(searchInput = searchInput)
            }.onSuccess {
                _managerFollowings.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managerFollowings.emit(DeferredData.Error(it.toString()))
            }
        }
    }
}

class ManagerInfoViewModel(
    private val getManagerInfoUseCase: GetManagerInfoUseCase,
): ViewModel() {
    private val _managerInfo = MutableStateFlow<DeferredData<ManagerInfo>>(DeferredData.Loading)
    val managerInfo: StateFlow<DeferredData<ManagerInfo>> = _managerInfo

    fun getManagerInfo(managerId: String) {
        viewModelScope.launch {
            _managerInfo.emit(DeferredData.Loading)
            kotlin.runCatching {
                getManagerInfoUseCase.execute(managerId)
            }.onSuccess {
                _managerInfo.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managerInfo.emit(DeferredData.Error(it.toString()))
            }
        }
    }
}

class SearchManagerViewModel(
    private val searchManagerUseCase: SearchManagerUseCase,
): ViewModel() {
    private val _managersList = MutableStateFlow<DeferredData<ManagerFollowers>>(DeferredData.Loading)
    val managersList: StateFlow<DeferredData<ManagerFollowers>> = _managersList

    fun searchManager(searchInput: String) {
        viewModelScope.launch {
            _managersList.emit(DeferredData.Loading)
            kotlin.runCatching {
                searchManagerUseCase.execute(searchInput = searchInput)
            }.onSuccess {
                _managersList.emit(DeferredData.Loaded(it))
            }.onFailure {
                _managersList.emit(DeferredData.Error(it.toString()))
            }
        }
    }
}

class FollowManagerViewModel(
    private val followManagerUseCase: FollowManagerUseCase,
    private val getManagerFollowingsUseCase: GetManagerFollowingsUseCase,
    ): ViewModel() {

    fun followManager(managerId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                followManagerUseCase.execute(managerId = managerId)
            }.onSuccess {
                getManagerFollowingsUseCase.execute()
            }.onFailure {
                getManagerFollowingsUseCase.execute()
            }
        }
    }

}