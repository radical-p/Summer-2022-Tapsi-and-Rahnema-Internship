package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class DashboardManagerDto(
    @SerializedName("username")
    val username: String,
    @SerializedName("budget")
    val credits: String,
    @SerializedName("teamId")
    val team: DashboardTeamDto,
)
