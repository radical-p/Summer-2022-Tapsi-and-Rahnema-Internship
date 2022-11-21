package com.example.fantasy.domain.usecase

import android.util.Log
import com.example.fantasy.domain.repository.DateAndWeekRepo
import com.example.fantasy.domain.repository.DateAndWeek
import kotlinx.coroutines.delay

class GetDateAndWeekUseCase(private val DateAndWeekRepo: DateAndWeekRepo) {
    suspend fun execute(): DateAndWeek {
        return DateAndWeekRepo.getDateAndWeek()
    }
}
