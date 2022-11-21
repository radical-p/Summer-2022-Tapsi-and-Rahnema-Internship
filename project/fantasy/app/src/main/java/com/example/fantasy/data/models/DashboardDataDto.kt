package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class DashboardDataDto(
    @SerializedName("data")
    val data: DashboardModelDto,
)