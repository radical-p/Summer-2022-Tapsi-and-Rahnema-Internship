package com.example.fantasy.domain.usecase

import com.example.fantasy.domain.repository.Player
import com.example.fantasy.domain.repository.PlayersInfoList
import com.example.fantasy.domain.repository.SearchPlayerRepo

class SearchPlayersUseCase(private val searchPlayerRepo: SearchPlayerRepo) {
    suspend fun execute(filter: Int, limit: Int, page: Int, searchInput: String): PlayersInfoList {
        return searchPlayerRepo.searchPlayer(filter, limit, page, searchInput)
    }
}