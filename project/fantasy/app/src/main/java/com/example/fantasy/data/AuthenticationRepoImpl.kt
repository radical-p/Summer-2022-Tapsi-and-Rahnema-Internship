package com.example.fantasy.data

import com.example.fantasy.data.api.LogInApi
import com.example.fantasy.data.api.SignUpApi
import com.example.fantasy.data.api.VerifyApi
import com.example.fantasy.data.models.toLoginModelVars
import com.example.fantasy.data.models.toVerificationModelVars
import com.example.fantasy.data.response.*
import com.example.fantasy.domain.repository.LoginRepo
import com.example.fantasy.domain.repository.SignupRepo
import com.example.fantasy.domain.repository.VerificationRepo

class SignUpRepoImpl(private val SignUpApi: SignUpApi): SignupRepo {
    override suspend fun signUp(signUpQuery: SignUpQuery): SignUpResponse {
        return SignUpApi.signUp(signUpQuery)
    }
}

class LoginRepoImpl(private val LoginInApi: LogInApi): LoginRepo {
    override suspend fun loginRepo(loginModelQuery: LoginModelQuery): LoginModelVars{
        return LoginInApi.login(loginModelQuery).toLoginModelVars()
    }
}

class VerificationRepoImpl(private val verifyApi: VerifyApi): VerificationRepo{
    override suspend fun verifyRepo(verificationQuery: VerificationQuery): VerificationResponseVars {
        return verifyApi.verify(verificationQuery).toVerificationModelVars()
    }
}