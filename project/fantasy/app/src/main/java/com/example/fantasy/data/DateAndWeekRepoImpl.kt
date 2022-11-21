package com.example.fantasy.data

import android.util.Log
import com.example.fantasy.data.api.DateAndWeekApi
import com.example.fantasy.data.models.toDateAndWeek
import com.example.fantasy.data.models.toDateAndWeekFields
import com.example.fantasy.domain.repository.DateAndWeek
import com.example.fantasy.domain.repository.DateAndWeekRepo

class DateAndWeekRepoImpl(private val DateAndWeekApi: DateAndWeekApi): DateAndWeekRepo {
    override suspend fun getDateAndWeek(): DateAndWeek {
        return DateAndWeekApi.getDateAndWeek().toDateAndWeekFields().toDateAndWeek()
    }
}