package com.example.fantasy.data.response

import com.example.fantasy.presentation.viewModels.DeferredData

data class SignupFieldState(
    val fields: DeferredData<SignupFields>
)

data class SignupFields(
    var first_name: String,
    var last_name: String,
    var email: String,
    var country: String,
    var username: String,
    var password: String
)