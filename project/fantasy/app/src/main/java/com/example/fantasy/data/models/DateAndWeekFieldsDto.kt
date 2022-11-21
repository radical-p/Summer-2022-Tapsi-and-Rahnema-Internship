package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class DateAndWeekFieldsDto(
    @SerializedName("deadline_time")
    val date: String,
    @SerializedName("name")
    val week: String,
)