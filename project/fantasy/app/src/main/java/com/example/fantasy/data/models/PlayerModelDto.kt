package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class PlayerModelDto(
    @SerializedName("web_name")
    val playerName: String,
    @SerializedName("plTeam")
    val playerTeam: PlayerTeamModelDto,
    @SerializedName("form")
    val playerRating: String,
    @SerializedName("now_cost")
    val playerPrice: String,
    @SerializedName("id")
    val playerId: String,
)