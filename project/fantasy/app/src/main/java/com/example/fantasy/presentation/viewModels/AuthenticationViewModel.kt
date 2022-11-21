package com.example.fantasy.presentation.viewModels

import Storage
import android.os.AsyncTask.execute
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.fantasy.data.LoginFieldState
import com.example.fantasy.data.LoginFields
import com.example.fantasy.data.response.*
import com.example.fantasy.domain.usecase.LoginUseCase
import com.example.fantasy.domain.usecase.SignUpUseCase
import com.example.fantasy.domain.usecase.VerificationUseCase
import com.example.fantasy.presentation.ui.Screen
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signupUseCase: SignUpUseCase,
    private val dataStore: Storage
) : ViewModel() {
    private val _fieldState =
        MutableStateFlow(
            SignupFieldState(
                DeferredData.Loaded(
                    SignupFields(
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            )
        )
    val fieldState: StateFlow<SignupFieldState> = _fieldState


    fun onSignupButtonClicked(signupFiledState: SignupFieldState, navController: NavController) {
        viewModelScope.launch {
            kotlin.runCatching {
                signupUseCase.execute(
                    SignUpQuery(
                        fieldState.value.fields.data!!.first_name,
                        fieldState.value.fields.data!!.last_name,
                        fieldState.value.fields.data!!.email,
                        fieldState.value.fields.data!!.country,
                        fieldState.value.fields.data!!.username,
                        fieldState.value.fields.data!!.password,
                    )
                )
            }.onSuccess {
                navController.navigate(
                    Screen.Verify.route
                )
                dataStore.insert("email", fieldState.value.fields.data!!.email)
            }.onFailure {

            }
        }
    }
}

class LogInViewModel(
    private val loginUseCase: LoginUseCase,
    private val dataStore: Storage,
) : ViewModel() {
    private val _fieldState =
        MutableStateFlow(LoginFieldState(DeferredData.Loaded(LoginFields("", "")), false))
    val fieldState: StateFlow<LoginFieldState> = _fieldState

    init {
        viewModelScope.launch {
            if(dataStore.get("token") != null) {
                _fieldState.value = _fieldState.value.copy(isLoggedIn = true)
            }
        }
    }

    fun onLoginButtonClicked(loginFiledState: LoginFieldState, navController: NavController) {
        viewModelScope.launch {
            kotlin.runCatching {
                loginUseCase.execute(
                    LoginModelQuery(
                        fieldState.value.fields.data!!.username,
                        fieldState.value.fields.data!!.password
                    )
                )
            }.onSuccess {
                dataStore.insert("token", it.token)
                navController.navigate(Screen.Home.route)
            }.onFailure {

            }
        }
    }
}

class VerificationViewModel(
    private val verificationUseCase: VerificationUseCase,
    private val dataStore: Storage
) : ViewModel() {

    private var requestJob: Job? = null

    private val _fieldState = MutableStateFlow(
        VerificationFieldState(
            DeferredData.Loaded(
                VerificationFields("", "")
            )
        )
    )

    val fieldState: StateFlow<VerificationFieldState> = _fieldState

    fun onVerificationButtonClicked(
        verificationFieldState: VerificationFieldState,
        navController: NavController
    ) {
        requestJob?.cancel()

        requestJob = viewModelScope.launch {
            kotlin.runCatching {
                val email = dataStore.get("email")
                verificationUseCase.execute(
                    VerificationQuery(
                        email = email!!,
                        code = verificationFieldState.fields.data?.code!!
                    )
                )
            }.onSuccess {
                dataStore.insert("token", it.token)
                navController.navigate(Screen.Home.route)
            }.onFailure {

            }
        }
    }
}