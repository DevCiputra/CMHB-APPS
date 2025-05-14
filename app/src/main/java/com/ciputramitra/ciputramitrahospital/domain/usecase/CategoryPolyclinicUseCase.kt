package com.ciputramitra.ciputramitrahospital.domain.usecase

import com.ciputramitra.ciputramitrahospital.domain.repository.categorypoly.CategoryPolyclinicRepository
import com.ciputramitra.ciputramitrahospital.response.categorypoly.CategoryPolyclinicResponse

class CategoryPolyclinicUseCase(
    private val categoryPolyclinicRepository: CategoryPolyclinicRepository
) {
    suspend fun fetchCategoryPolyclinic(): Result<CategoryPolyclinicResponse> {
        return categoryPolyclinicRepository.fetchCategoryPolyclinic()
    }
}