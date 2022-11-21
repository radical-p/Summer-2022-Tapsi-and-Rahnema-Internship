package com.example.fantasy.domain.repository

import com.example.fantasy.data.models.PlayerIdsDto

interface AddToTeamRepo {
    suspend fun addToTeam(id: String, index: Int): List<PlayerIdsDto>
}

interface RemoveFromTeamRepo {
    suspend fun removeFromTeam(id: String, index: Int): List<PlayerIdsDto>
}