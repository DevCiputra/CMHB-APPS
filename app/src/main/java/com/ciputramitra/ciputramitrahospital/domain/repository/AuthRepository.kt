package com.ciputramitra.ciputramitrahospital.domain.repository

import com.ciputramitra.ciputramitrahospital.response.auth.SignResponse
import com.ciputramitra.ciputramitrahospital.response.auth.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(username: String, email:String, password: String, passwordConfirm: String, role:String): Result<SignResponse>
    suspend fun login(username: String, password: String): Result<SignResponse>
    suspend fun saveToken(token: String)
    suspend fun saveUser(user: User)
    suspend fun getToken() : Flow<String?>
    suspend fun getUser(): Flow<User?>
    suspend fun logout()
}