package com.ciputramitra.ciputramitrahospital.domain.repository.home

import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.category.CategoryResponse
import retrofit2.HttpException

class HomeRepositoryImpl(
    private val apiService: ApiService
): HomeRepository {
    override suspend fun fetchCategory(): Result<CategoryResponse> {
        return try {
            val response = apiService.fetchCategory()
            when(response.meta.code == 200) {
                true -> Result.success(value = response)
                false -> Result.failure(exception = Exception(response.meta.message))
            }
        }
        catch (e: Exception) {
            when {
                e is HttpException && e.code() == 500 -> {
                    Result.failure(exception =  Exception("Server sedang sibuk"))
                }

                else -> Result.failure(exception = e)
            }
        }
    }


}