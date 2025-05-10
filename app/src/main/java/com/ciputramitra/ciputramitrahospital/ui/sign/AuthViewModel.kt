package com.ciputramitra.ciputramitrahospital.ui.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.domain.usecase.AuthUseCase
import com.ciputramitra.ciputramitrahospital.response.auth.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase
): ViewModel() {
    private val _authState = MutableStateFlow<StateManagement>(StateManagement.Idle)
    val authState: StateFlow<StateManagement> = _authState.asStateFlow()

    private val _token = MutableStateFlow<String?>(value = null)
    val token: StateFlow<String?> = _token.asStateFlow()

    private val _fetchUser = MutableStateFlow<User?>(value = null)
    val fetchUser: StateFlow<User?> = _fetchUser.asStateFlow()


    val isLoggedIn = _token.map { it != null }
    init {
        viewModelScope.launch {
            authUseCase.getUser().collect { user ->
                _fetchUser.value = user
            }
        }

        viewModelScope.launch {
            authUseCase.getToken().collect { token ->
                _token.value = token
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = StateManagement.Loading
            val result = authUseCase.invoke(email, password)
            result.fold(
                onSuccess = { signResponse ->
                    authUseCase.invoke(user = signResponse.user)
                    authUseCase.invoke(token = signResponse.accessToken)
                    _authState.value = StateManagement.LoginSuccess( signResponse = signResponse)
                },
                onFailure = { error ->
                    _authState.value = StateManagement.Error(error.message ?: "Not Found")
                }
            )
        }
    }

    fun register(
        username: String,
        email: String,
        password: String,
        passwordConfirmation:String,
        role:String,
        whatsaap: String,
        kota: String,
        provinsi: String,
        status_aktif: String,
        fcm: String
    ) {
        viewModelScope.launch {
            _authState.value = StateManagement.Loading
            val result = authUseCase.invoke(username, email, password, passwordConfirmation, role, whatsaap, kota, provinsi, status_aktif, fcm)
            result.fold(
                onSuccess = { signResponse ->
                    authUseCase.invoke(user = signResponse.user)
                    authUseCase.invoke(token = signResponse.accessToken)
                    _authState.value = StateManagement.RegisterSuccess(signResponse = signResponse)
                },
                onFailure = {
                    _authState.value = StateManagement.Error(message = "not Found")
                }
            )
        }
    }


    fun clearAuthState() {
        viewModelScope.launch {
            _authState.value = StateManagement.Idle
        }
    }
}