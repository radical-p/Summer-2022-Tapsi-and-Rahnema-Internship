package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class PlayerTeamModelDto(
    @SerializedName("short_name")
    val team: String,
)