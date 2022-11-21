package com.example.fantasy.domain.repository

interface DateAndWeekRepo {
    suspend fun getDateAndWeek(): DateAndWeek
}