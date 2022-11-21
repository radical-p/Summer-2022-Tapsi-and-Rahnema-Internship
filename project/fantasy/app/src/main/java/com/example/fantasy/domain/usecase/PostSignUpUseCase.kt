package com.example.fantasy.domain.usecase

import com.example.fantasy.data.response.*
import com.example.fantasy.domain.repository.LoginRepo
import com.example.fantasy.domain.repository.SignUpModel
import com.example.fantasy.domain.repository.SignupRepo
import com.example.fantasy.domain.repository.VerificationRepo

class SignUpUseCase(private val signUpRepo: SignupRepo) {
    suspend fun execute(signUpQuery: SignUpQuery): SignUpResponse {
        return signUpRepo.signUp(signUpQuery)
    }
}


class LoginUseCase(private val loginRepo: LoginRepo) {
    suspend fun execute(loginModelQuery: LoginModelQuery): LoginModelVars {
        return loginRepo.loginRepo(loginModelQuery)
    }
}


class VerificationUseCase(private val verificationRepo: VerificationRepo) {
    suspend fun execute(verificationQuery: VerificationQuery): VerificationResponseVars {
        return verificationRepo.verifyRepo(verificationQuery)
    }
}