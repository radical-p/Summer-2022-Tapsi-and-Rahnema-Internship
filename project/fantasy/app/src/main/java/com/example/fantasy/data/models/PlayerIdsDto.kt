package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class PlayerIdsDto(
    @SerializedName("player")
    val playerId: String,
)
