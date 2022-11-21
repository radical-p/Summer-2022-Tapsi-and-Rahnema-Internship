package com.example.fantasy.domain.repository

interface DashboardRepo {
    suspend fun getDashboard(): Dashboard
}