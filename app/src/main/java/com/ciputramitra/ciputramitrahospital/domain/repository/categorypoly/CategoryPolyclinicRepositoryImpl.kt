package com.ciputramitra.ciputramitrahospital.domain.repository.categorypoly

import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.categorypoly.CategoryPolyclinicResponse
import retrofit2.HttpException

class CategoryPolyclinicRepositoryImpl(
    private val apiService: ApiService
): CategoryPolyclinicRepository {
    override suspend fun fetchCategoryPolyclinic(): Result<CategoryPolyclinicResponse> {
        return try {
            val response = apiService.fetchCategoryPolyclinic()
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