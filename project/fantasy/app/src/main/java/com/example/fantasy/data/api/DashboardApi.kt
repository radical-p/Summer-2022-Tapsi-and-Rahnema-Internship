package com.example.fantasy.data.api

import com.example.fantasy.data.models.DashboardDataDto
import com.example.fantasy.data.models.DashboardModelDto
import retrofit2.http.GET
import retrofit2.http.Header

interface DashboardApi {
    @GET("managers/dashboard")
    suspend fun getDashboard(@Header("token") token: String): DashboardDataDto
}