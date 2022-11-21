package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class InTeamSinglePlayerModelDto(
    @SerializedName("web_name")
    val name: String,
    @SerializedName("form")
    val rating: String,
    @SerializedName("now_cost")
    val price: String,
    @SerializedName("id")
    val id: String,
    val isInTeam: Boolean,
)