package com.example.fantasy.domain.usecase

import com.example.fantasy.domain.repository.ManagerProfileInfo
import com.example.fantasy.domain.repository.ManagerProfileRepo
import com.example.fantasy.domain.repository.ManagerProfileRequest
import com.example.fantasy.domain.repository.UpdateProfileRepo

class GetManagerProfileUseCase(private val managerProfileRepo: ManagerProfileRepo) {
    suspend fun execute(): ManagerProfileInfo {
        return managerProfileRepo.getManagerProfile()
    }
}

class UpdateManagerProfileUseCase(private val updateProfileRepo: UpdateProfileRepo) {
    suspend fun execute(managerProfileRequest: ManagerProfileRequest): ManagerProfileInfo {
        return updateProfileRepo.updateManagerProfile(managerProfileRequest)
    }
}