package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class PlayersDataDto(
    @SerializedName("data")
    val data: List<PlayerModelDto>,
    @SerializedName("total")
    val total: Int,
)