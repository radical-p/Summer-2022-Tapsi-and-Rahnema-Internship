package com.example.fantasy.data

import Storage
import com.example.fantasy.data.api.PlayerApi
import com.example.fantasy.data.models.toPlayer
import com.example.fantasy.data.models.toPlayerModel
import com.example.fantasy.domain.repository.Player
import com.example.fantasy.domain.repository.PlayerRepo
import com.example.fantasy.domain.repository.PlayersInfoList

class PLayerRepoImpl(
    private val playerApi: PlayerApi,
    private val dataStore: Storage,
): PlayerRepo {
    override suspend fun getPlayers(filter: Int, limit: Int, page: Int): PlayersInfoList {
        val token = dataStore.get("token")
        return playerApi.getPlayers(token!!,"",filter, page, limit).toPlayerModel()
    }
}