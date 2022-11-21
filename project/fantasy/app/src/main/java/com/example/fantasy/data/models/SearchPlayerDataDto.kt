package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class SearchPlayerDataDto(
    @SerializedName("data")
    val data: SearchPlayerResultsDto,
)

//apply change in api

data class SearchPlayerResultsDto(
    @SerializedName("array")
    val playersList: List<PlayerModelDto>,
    @SerializedName("total")
    val total: Int,
)