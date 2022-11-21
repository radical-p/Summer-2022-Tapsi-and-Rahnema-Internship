package com.example.fantasy.data.models

import android.media.Image
import com.google.gson.annotations.SerializedName

data class ManagerFollowingsDto(
    @SerializedName("data")
    val managerFollowings: List<ManagerFollowingDto>
)

data class ManagerFollowingDto(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("img")
    val managerImage: String?,
    @SerializedName("managerId")
    val managerId: String,
    @SerializedName("following")
    val isFollowed: Boolean,
)