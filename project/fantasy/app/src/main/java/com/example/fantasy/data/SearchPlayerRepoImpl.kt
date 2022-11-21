package com.example.fantasy.data

import Storage
import com.example.fantasy.data.api.SearchPlayerApi
import com.example.fantasy.data.models.toPlayer
import com.example.fantasy.data.models.toPlayerModel
import com.example.fantasy.domain.repository.Player
import com.example.fantasy.domain.repository.PlayersInfoList
import com.example.fantasy.domain.repository.SearchPlayerRepo

class SearchPlayerRepoImpl(
    private val searchPlayerApi: SearchPlayerApi,
    private val dataStore: Storage,
): SearchPlayerRepo {
    override suspend fun searchPlayer(filter: Int, limit: Int, page: Int, searchInput: String): PlayersInfoList {
        val token = dataStore.get("token")
        return searchPlayerApi.searchPlayer(token!!, filter, page, limit, searchInput).toPlayerModel()
    }
}