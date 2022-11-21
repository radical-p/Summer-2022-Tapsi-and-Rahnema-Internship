package com.example.fantasy.domain.repository

interface SearchPlayerRepo {
    suspend fun searchPlayer(filter: Int, limit: Int, page: Int, searchInput: String): PlayersInfoList
}