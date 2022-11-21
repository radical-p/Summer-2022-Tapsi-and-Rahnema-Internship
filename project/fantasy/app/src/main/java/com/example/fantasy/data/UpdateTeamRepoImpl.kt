package com.example.fantasy.data

import Storage
import com.example.fantasy.data.api.AddToTeamApi
import com.example.fantasy.data.api.RemoveFromTeamApi
import com.example.fantasy.data.models.PlayerIdsDto
import com.example.fantasy.data.models.PlayerInfo
import com.example.fantasy.data.models.toPlayerIdsDtoList
import com.example.fantasy.domain.repository.AddToTeamRepo
import com.example.fantasy.domain.repository.RemoveFromTeamRepo
import retrofit2.http.Header

class RemoveFromTeamImpl(
    private val removeFromTeamApi: RemoveFromTeamApi,
    private val dataStore: Storage,
    ): RemoveFromTeamRepo {
    override suspend fun removeFromTeam(id: String, index: Int): List<PlayerIdsDto> {
        val token = dataStore.get("token")
        return removeFromTeamApi.removeFromTeam(PlayerInfo(id, index), token!!).toPlayerIdsDtoList()
    }
}

class AddToTeamImpl(
    private val addToTeamApi: AddToTeamApi,
    private val dataStore: Storage,
    ): AddToTeamRepo {
    override suspend fun addToTeam(id: String, index: Int): List<PlayerIdsDto> {
        val token = dataStore.get("token")
        return addToTeamApi.addToTeam(PlayerInfo(id, index), token!!).toPlayerIdsDtoList()
    }
}