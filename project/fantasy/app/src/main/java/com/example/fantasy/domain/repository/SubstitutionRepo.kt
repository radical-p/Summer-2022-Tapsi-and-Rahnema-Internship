package com.example.fantasy.domain.repository

interface SubstitutionRepo {
    suspend fun substitutePlayer(inPlayer: Int, outPlayer: Int): Unit
}