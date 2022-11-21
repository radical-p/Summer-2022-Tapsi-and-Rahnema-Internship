package com.example.fantasy.data.models

import android.media.Image
import com.google.gson.annotations.SerializedName

data class ManagerInfoDto(
    @SerializedName("data")
    val managerInfoItems: ManagerInfoItems
)

data class ManagerInfoItems(
    @SerializedName("")
    val fullName: String,
    @SerializedName("")
    val managerImage: Image,
    @SerializedName("")
    val age: Int,
    @SerializedName("")
    val country: String,
    @SerializedName("")
    val points: String,
)