package com.example.fantasy.data.response

import com.google.gson.annotations.SerializedName

data class VerificationResponse(
    @SerializedName("data")
    val data: VerificationResponseVars
)

data class VerificationResponseVars(
    @SerializedName("token")
    val token: String
)