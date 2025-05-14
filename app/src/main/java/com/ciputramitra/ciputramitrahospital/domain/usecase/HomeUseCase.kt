package com.ciputramitra.ciputramitrahospital.domain.usecase

import com.ciputramitra.ciputramitrahospital.domain.repository.home.HomeRepository
import com.ciputramitra.ciputramitrahospital.response.category.CategoryResponse

class HomeUseCase(
    private val homeRepository: HomeRepository
) {
    suspend fun fetchCategory(): Result<CategoryResponse> {
        return homeRepository.fetchCategory()
    }
}