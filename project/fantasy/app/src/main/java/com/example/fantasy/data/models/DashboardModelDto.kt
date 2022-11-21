package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class DashboardModelDto(
    @SerializedName("nb")
    val remainingPlayers: Int,
    @SerializedName("manager")
    val manager: DashboardManagerDto,
)