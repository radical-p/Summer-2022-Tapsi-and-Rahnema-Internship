package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class InTeamPlayerModelDto(
    @SerializedName("player")
    val player: InTeamSinglePlayerModelDto?,
)