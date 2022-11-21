package com.example.fantasy.data.models
import com.google.gson.annotations.SerializedName

data class DateAndWeekModelDto(
    @SerializedName("data")
    val data: DateAndWeekFieldsDto,
)