package com.example.fantasy.data.models

import android.media.Image
import com.google.gson.annotations.SerializedName

data class ManagerFollowersDto(
    @SerializedName("data")
    val managerFollowers: List<ManagerFollowerDto>
)

data class ManagerFollowerDto(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("img")
    val managerImage: String? = "picsun.photos/100/100",
    @SerializedName("managerId")
    val managerId: String,
    @SerializedName("following")
    val isFollowed: Boolean,
)