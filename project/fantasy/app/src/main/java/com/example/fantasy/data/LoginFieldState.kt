package com.example.fantasy.data

import com.example.fantasy.presentation.viewModels.DeferredData

data class LoginFieldState(
    val fields: DeferredData<LoginFields>,
    var isLoggedIn: Boolean,
)

data class LoginFields(
    var username: String,
    var password: String
)