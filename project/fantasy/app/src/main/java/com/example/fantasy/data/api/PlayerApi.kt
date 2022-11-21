package com.example.fantasy.data.api

import com.example.fantasy.data.models.PlayersDataDto
import com.example.fantasy.data.models.SearchPlayerDataDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {
    @GET("players/search")
    suspend fun getPlayers(@Header("token") token: String,  @Query("web_name") input: String, @Query("filter") filter: Int, @Query("page") page: Int, @Query("limit") limit: Int): SearchPlayerDataDto
}