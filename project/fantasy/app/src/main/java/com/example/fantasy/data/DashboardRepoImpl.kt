package com.example.fantasy.data

import Storage
import android.util.Log
import com.example.fantasy.data.api.DashboardApi
import com.example.fantasy.data.models.toDashboard
import com.example.fantasy.data.models.toDashboardModelDto
import com.example.fantasy.domain.repository.Dashboard
import com.example.fantasy.domain.repository.DashboardRepo

class DashboardRepoImpl(
    private val dashboardApi: DashboardApi,
    private val dataStore: Storage,
): DashboardRepo {
    override suspend fun getDashboard(): Dashboard {
        val token = dataStore.get("token")
        return dashboardApi.getDashboard(token!!).toDashboardModelDto().toDashboard()
    }
}