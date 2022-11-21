package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class UpdatedTeamDto(
    @SerializedName("data")
    val data: List<PlayerIdsDto>
)
