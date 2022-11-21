package com.example.fantasy.domain.usecase

import android.util.Log
import com.example.fantasy.domain.repository.Player
import com.example.fantasy.domain.repository.PlayerRepo
import com.example.fantasy.domain.repository.PlayersInfoList
import kotlinx.coroutines.delay

class GetPlayersListUseCase(private val playerRepo: PlayerRepo) {
    suspend fun execute(filter: Int, limit: Int, page: Int): PlayersInfoList {
        return playerRepo.getPlayers(filter, limit, page)
    }
}