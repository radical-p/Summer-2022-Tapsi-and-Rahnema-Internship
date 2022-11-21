package com.example.fantasy.data.response

import com.google.gson.annotations.SerializedName

data class VerificationQuery(
    val email: String,
    val code: String
)

