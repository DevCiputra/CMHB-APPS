package com.ciputramitra.ciputramitrahospital.ui.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciputramitra.ciputramitrahospital.domain.remote.HttpClient
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.domain.usecase.AuthUseCase
import com.ciputramitra.ciputramitrahospital.response.auth.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val httpClient: HttpClient
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
                    if (signResponse.user.role != "Pasien") {
                        // Jika bukan Pasien, jangan simpan data dan langsung kirim error
                        _authState.value = StateManagement.Error("Hanya untuk Pasien")
                        return@fold

                    }

                    if (signResponse.user.statusAktif == "online") {
                        _authState.value = StateManagement.Error("Account sedang login saat ini")
                        return@fold
                    }
                    // Simpan data user dan token hanya jika role adalah Pasien
                    authUseCase.invoke(user = signResponse.user)
                    authUseCase.invoke(token = signResponse.accessToken)
                    _authState.value = StateManagement.LoginSuccess(signResponse = signResponse)

                    // Rebuild HTTP client agar menggunakan token baru
                    httpClient.rebuildClient() // Inject httpClient ke ViewModel

                },
                onFailure = { error ->
                    _authState.value = StateManagement.Error(error.message.toString())
                }
            )
        }
    }

    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation:String,
        role:String,
        whatsaap: String,
        address: String,
        status_aktif: String,
        fcm: String
    ) {
        viewModelScope.launch {
            _authState.value = StateManagement.Loading
            val result = authUseCase.invoke(name, email, password, passwordConfirmation, role, whatsaap, address, status_aktif, fcm)
            result.fold(
                onSuccess = { signResponse ->
                    // Simpan data user dan token hanya jika role adalah Pasien
                    authUseCase.invoke(user = signResponse.user)
                    authUseCase.invoke(token = signResponse.accessToken)
                    _authState.value = StateManagement.RegisterSuccess(signResponse = signResponse)

                    // Rebuild HTTP client agar menggunakan token baru
                    httpClient.rebuildClient() // Inject httpClient ke ViewModel
                },
                onFailure = { error ->
                    _authState.value = StateManagement.Error(error.message.toString())
                }
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                authUseCase.invoke()
                _authState.value = StateManagement.Idle
                _fetchUser.value = null
                _token.value = null
            } catch (e: Exception) {
                error(e.message ?: "Logout gagal")
            }

        }
    }

    fun clearAuthState() {
        viewModelScope.launch {
            _authState.value = StateManagement.Idle
        }
    }
}