package com.example.fantasy.data.response

import com.google.gson.annotations.SerializedName

data class LoginModelVars (
    @SerializedName("manager")
    val managerId: String,
    @SerializedName("token")
    val token: String
)