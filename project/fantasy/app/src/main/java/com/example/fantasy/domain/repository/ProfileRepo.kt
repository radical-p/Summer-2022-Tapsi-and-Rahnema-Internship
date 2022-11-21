package com.example.fantasy.domain.repository

interface ManagerProfileRepo {
    suspend fun getManagerProfile(): ManagerProfileInfo
}

interface UpdateProfileRepo {
    suspend fun updateManagerProfile(managerProfileRequest: ManagerProfileRequest): ManagerProfileInfo
}