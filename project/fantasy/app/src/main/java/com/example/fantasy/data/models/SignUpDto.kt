package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class SignUpModelDto(
    @SerializedName("")
    val name: String,
    @SerializedName("")
    val lastname: String,
    @SerializedName("")
    val email: String,
    @SerializedName("")
    val country: String,
    @SerializedName("")
    val username: String,
    @SerializedName("")
    val password: String
)
