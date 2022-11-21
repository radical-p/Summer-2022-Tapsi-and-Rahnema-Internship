package com.example.fantasy.domain.usecase

import com.example.fantasy.domain.repository.SubstitutionRepo

class SubstitutionUseCase(private val substitutionRepo: SubstitutionRepo) {
    suspend fun execute(inPlayer: Int, outPlayer: Int): Unit {
        return substitutionRepo.substitutePlayer(inPlayer, outPlayer)
    }
}