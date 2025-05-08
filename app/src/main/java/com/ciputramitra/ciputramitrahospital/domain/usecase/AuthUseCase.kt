package com.ciputramitra.ciputramitrahospital.domain.usecase

import com.ciputramitra.ciputramitrahospital.domain.repository.AuthRepository
import com.ciputramitra.ciputramitrahospital.response.auth.SignResponse
import com.ciputramitra.ciputramitrahospital.response.auth.User
import kotlinx.coroutines.flow.Flow

class AuthUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, email: String, password: String, passwordConfirm: String, role:String): Result<SignResponse> {
        return authRepository.register(username, email, password, passwordConfirm, role)
    }

    suspend operator fun invoke(username: String, password: String): Result<SignResponse> {
        return authRepository.login(username = username, password = password)
    }

    suspend operator fun invoke(token: String) {
        authRepository.saveToken(token = token)
    }

    suspend operator fun invoke(user: User) {
        authRepository.saveUser(user = user)
    }

    suspend fun getToken(): Flow<String?> {
        return authRepository.getToken()
    }

    suspend fun getUser(): Flow<User?> {
        return authRepository.getUser()
    }

    suspend operator fun invoke() {
        authRepository.logout()
    }
}