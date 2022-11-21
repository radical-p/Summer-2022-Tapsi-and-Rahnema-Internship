package com.example.fantasy.domain.repository

interface PlayerRepo {
    suspend fun getPlayers(filter: Int, limit: Int, page: Int): PlayersInfoList
}