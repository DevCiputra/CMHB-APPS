package com.ciputramitra.ciputramitrahospital.domain.repository

import com.ciputramitra.ciputramitrahospital.response.auth.SignResponse
import com.ciputramitra.ciputramitrahospital.response.auth.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirm:String,
        role:String,
        whatsaap: String,
        address: String,
        status_aktif: String,
        fcm: String): Result<SignResponse>

    suspend fun login(email: String, password: String): Result<SignResponse>
    suspend fun saveToken(token: String)
    suspend fun saveUser(user: User)
    suspend fun getToken() : Flow<String?>
    suspend fun getUser(): Flow<User?>
    suspend fun logout()
}