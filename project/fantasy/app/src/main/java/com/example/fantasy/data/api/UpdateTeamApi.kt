package com.example.fantasy.data.api

import com.example.fantasy.data.models.PlayerInfo
import com.example.fantasy.data.models.UpdatedTeamDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface AddToTeamApi {
    @PATCH("teams/add-player")
    suspend fun addToTeam(@Body playerInfo: PlayerInfo, @Header("token") token: String): UpdatedTeamDto
}

interface RemoveFromTeamApi {
    @PATCH("teams/delete-player")
    suspend fun removeFromTeam(@Body playerInfo: PlayerInfo, @Header("token") token: String): UpdatedTeamDto
}