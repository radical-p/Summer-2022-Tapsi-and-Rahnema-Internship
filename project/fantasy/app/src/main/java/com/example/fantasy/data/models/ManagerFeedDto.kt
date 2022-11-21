package com.example.fantasy.data.models

import android.media.Image
import com.google.gson.annotations.SerializedName

data class ManagerFeedDto(
    @SerializedName("data")
    val ManagerFeedList: List<MangerFeedItemDto>
)

data class MangerFeedItemDto(
    @SerializedName("img")
    val managerImage: Image?,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("is_liked")
    val isLiked: Boolean,
    @SerializedName("points")
    val points: String,
    @SerializedName("event")
    val event: String,
    @SerializedName("substitutions")
    val substitutions: List<SubstitutionItemDto>,
    @SerializedName("managerId")
    val managerId: String,
    @SerializedName("feedId")
    val feedId: String,
)

data class SubstitutionItemDto(
    @SerializedName("in")
    val inPlayer: String,
    @SerializedName("out")
    val outPlayer: String,
)