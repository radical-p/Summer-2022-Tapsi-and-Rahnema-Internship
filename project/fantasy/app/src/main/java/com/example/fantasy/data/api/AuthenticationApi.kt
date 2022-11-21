package com.example.fantasy.data.api

import com.example.fantasy.data.models.SignUpDto
import com.example.fantasy.data.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpApi{
    @POST("auth/signup")
    suspend fun signUp(@Body signUpQuery: SignUpQuery): SignUpResponse
}

interface LogInApi{
    @POST("auth/login")
    suspend fun login(@Body loginModelQuery: LoginModelQuery): LoginModelResponse
}

interface VerifyApi{
    @POST("auth/verify")
    suspend fun verify(@Body verificationQuery: VerificationQuery): VerificationResponse
}