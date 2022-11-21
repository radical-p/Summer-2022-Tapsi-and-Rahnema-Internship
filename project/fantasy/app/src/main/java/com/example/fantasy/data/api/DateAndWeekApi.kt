package com.example.fantasy.data.api

import com.example.fantasy.data.models.DateAndWeekModelDto
import retrofit2.http.GET

interface DateAndWeekApi {
    @GET("events/current/info")
    suspend fun getDateAndWeek(): DateAndWeekModelDto
}