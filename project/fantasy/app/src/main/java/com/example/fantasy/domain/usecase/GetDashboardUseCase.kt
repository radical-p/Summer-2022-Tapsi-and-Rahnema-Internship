package com.example.fantasy.domain.usecase

import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.domain.repository.DashboardRepo
import kotlinx.coroutines.delay

class GetDashboardUseCase(private val dashboardRepo: DashboardRepo) {
    suspend fun execute(): Dashboard {
        return dashboardRepo.getDashboard()
    }
}