package com.example.fantasy.data.api

import com.example.fantasy.data.models.SubstitutionInfo
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface SubstitutionApi {
    @PATCH("team")
    suspend fun substitutePlayer(@Header("token") token: String, @Body substitutionInfo: SubstitutionInfo): Unit
}