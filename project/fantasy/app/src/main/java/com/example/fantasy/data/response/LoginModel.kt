package com.example.fantasy.data.response

import com.google.gson.annotations.SerializedName

data class LoginModelResponse(
    @SerializedName("data")
    var data: LoginModelVars
)

data class LoginModelQuery(
    val username: String,
    val password: String
)