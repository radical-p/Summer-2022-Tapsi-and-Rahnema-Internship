package com.example.fantasy.data.response

data class SignUpQuery (
    val first_name: String,
    val last_name: String,
    val email: String,
    val country: String,
    val username: String,
    val password: String,
)

data class SignUpResponse(
    val tmp: String
)