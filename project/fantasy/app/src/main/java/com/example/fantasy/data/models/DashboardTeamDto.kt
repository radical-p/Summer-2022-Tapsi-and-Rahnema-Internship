package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class DashboardTeamDto(
    @SerializedName("picks")
    val team: List<InTeamPlayerModelDto>
)
