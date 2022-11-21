package com.example.fantasy.domain.repository

import com.example.fantasy.data.response.*


interface LoginRepo{
    suspend fun loginRepo(loginModelQuery: LoginModelQuery): LoginModelVars
}

interface SignupRepo {
    suspend fun signUp(signUpQuery: SignUpQuery): SignUpResponse
}

interface  VerificationRepo {
    suspend fun verifyRepo(verificationQuery: VerificationQuery): VerificationResponseVars
}