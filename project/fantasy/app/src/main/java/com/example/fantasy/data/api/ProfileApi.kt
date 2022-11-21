package com.example.fantasy.data.api

import com.example.fantasy.data.models.ManagerProfileDataDto
import com.example.fantasy.domain.repository.ManagerProfileRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface ManagerProfileApi {
    @GET("managers/profile")
    suspend fun getManagerProfile(@Header("token") token: String): ManagerProfileDataDto
}

interface UpdateProfileApi {
    @PATCH("managers/update")
    suspend fun updateManagerProfile(@Header("token") token: String, @Body managerProfileRequest: ManagerProfileRequest): ManagerProfileDataDto
}