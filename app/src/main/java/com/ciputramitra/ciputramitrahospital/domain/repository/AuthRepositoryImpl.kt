package com.ciputramitra.ciputramitrahospital.domain.repository

import com.ciputramitra.ciputramitrahospital.datastore.DataStoreManager
import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.auth.SignResponse
import com.ciputramitra.ciputramitrahospital.response.auth.User
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val dataStoreManager: DataStoreManager
): AuthRepository {
    override suspend fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirm:String,
        role:String,
        whatsaap: String,
        address: String,
        status_aktif: String,
        fcm: String
    ): Result<SignResponse> {
        return try {
            val response = apiService.register(
                name, email, password, passwordConfirm, role, whatsaap, address, status_aktif, fcm
            )
            when(response.meta?.code == 200 && response.data != null) {
                true -> Result.success(value = response.data)
                false -> Result.failure(exception = Exception(response.meta?.message))
            }
        }
        catch (e: Exception) {
            when {
                e is HttpException && e.code() == 500 -> {
                    Result.failure(exception =  Exception("Server sedang sibuk"))
                }

                e is HttpException && e.code() == 400 -> {
                    Result.failure(exception =  Exception("The email has already been taken."))
                }
                else -> Result.failure(exception = e)
            }
        }
    }

    override suspend fun login(email: String, password: String): Result<SignResponse> {
        return try {
            val response = apiService.login(email = email, password = password)
            when(response.meta?.code == 200 && response.data != null) {
                true -> Result.success(value = response.data)
                false -> Result.failure(exception = Exception(response.meta?.message ?: "Not Found"))
            }
        }
        catch (e: Exception) {
            when {
                e is HttpException && e.code() == 500 -> {
                    Result.failure(exception =  Exception("Server sedang sibuk"))
                }

                e is HttpException && e.code() == 400 -> {
                    Result.failure(exception =  Exception("Account tidak bisa di akses"))
                }
                else -> Result.failure(exception = e)

            }
        }
    }

    override suspend fun saveToken(token: String) {
        dataStoreManager.saveToken(token = token)
    }

    override suspend fun saveUser(user: User) {
        dataStoreManager.saveUser(user = user)
    }

    override suspend fun getToken(): Flow<String?> {
        return dataStoreManager.tokenFlow
    }

    override suspend fun getUser(): Flow<User?> {
        return dataStoreManager.userFlow
    }

    override suspend fun logout() {
        dataStoreManager.clearDataStore()
    }
}