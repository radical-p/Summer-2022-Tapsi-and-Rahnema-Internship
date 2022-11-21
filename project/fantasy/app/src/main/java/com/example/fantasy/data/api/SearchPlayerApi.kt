package com.example.fantasy.data.api

import com.example.fantasy.data.models.PlayersDataDto
import com.example.fantasy.data.models.SearchPlayerDataDto
import retrofit2.http.*

interface SearchPlayerApi {
    @GET("players/search")
    suspend fun searchPlayer(@Header("token") token: String, @Query("filter") filter: Int, @Query("page") page: Int, @Query("limit") limit: Int, @Query("web_name") searchInput: String): SearchPlayerDataDto
}