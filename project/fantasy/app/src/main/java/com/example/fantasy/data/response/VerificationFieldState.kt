package com.example.fantasy.data.response

import com.example.fantasy.presentation.viewModels.DeferredData

data class VerificationFieldState(
    val fields: DeferredData<VerificationFields>
)

data class VerificationFields(
    var email: String,
    var code: String
)