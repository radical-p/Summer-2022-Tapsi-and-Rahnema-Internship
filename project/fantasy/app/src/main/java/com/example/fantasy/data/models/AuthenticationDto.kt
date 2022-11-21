package com.example.fantasy.data.models

import com.google.gson.annotations.SerializedName

data class SignUpDto(
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("password")
    val password: String
)