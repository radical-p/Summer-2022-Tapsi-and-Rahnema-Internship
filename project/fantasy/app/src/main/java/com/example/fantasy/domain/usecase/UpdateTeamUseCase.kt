package com.example.fantasy.domain.usecase

import com.example.fantasy.data.models.PlayerIdsDto
import com.example.fantasy.domain.repository.AddToTeamRepo
import com.example.fantasy.domain.repository.RemoveFromTeamRepo

class RemoveFromTeamUseCase(private val removeFromTeamRepo: RemoveFromTeamRepo) {
        suspend fun execute(id: String, index: Int): List<PlayerIdsDto> {
            return removeFromTeamRepo.removeFromTeam(id, index)
        }
}

class AddToTeamUseCase(private val addToTeamRepo: AddToTeamRepo) {
    suspend fun execute(id: String, index: Int): List<PlayerIdsDto> {
        return addToTeamRepo.addToTeam(id, index)
    }
}