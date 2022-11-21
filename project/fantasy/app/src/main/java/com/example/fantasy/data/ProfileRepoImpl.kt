package com.example.fantasy.data

import Storage
import com.example.fantasy.data.api.ManagerProfileApi
import com.example.fantasy.data.api.UpdateProfileApi
import com.example.fantasy.data.models.toManagerProfile
import com.example.fantasy.domain.repository.ManagerProfileInfo
import com.example.fantasy.domain.repository.ManagerProfileRepo
import com.example.fantasy.domain.repository.ManagerProfileRequest
import com.example.fantasy.domain.repository.UpdateProfileRepo

class ManagerProfileRepoImpl(
    private val managerProfileApi: ManagerProfileApi,
    private val dataStore: Storage,
): ManagerProfileRepo {
    override suspend fun getManagerProfile(): ManagerProfileInfo {
        val token = dataStore.get("token")
        return managerProfileApi.getManagerProfile(token!!).toManagerProfile()
    }
}

class UpdateProfileRepoImpl(
    private val updateProfileApi: UpdateProfileApi,
    private val dataStore: Storage,
): UpdateProfileRepo {
    override suspend fun updateManagerProfile(managerProfileRequest: ManagerProfileRequest): ManagerProfileInfo {
        val token = dataStore.get("token")
        return updateProfileApi.updateManagerProfile(token!!, managerProfileRequest).toManagerProfile()
    }
}