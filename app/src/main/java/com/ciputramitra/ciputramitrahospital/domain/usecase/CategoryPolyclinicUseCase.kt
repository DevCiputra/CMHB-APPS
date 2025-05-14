package com.ciputramitra.ciputramitrahospital.domain.usecase

import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.domain.repository.categorypoly.CategoryPolyclinicRepository
import com.ciputramitra.ciputramitrahospital.response.categoryPoly.Data
import kotlinx.coroutines.flow.Flow

class CategoryPolyclinicUseCase(
    private val categoryPolyclinicRepository: CategoryPolyclinicRepository
) {
    suspend fun fetchCategoryPolyclinic():Flow<PagingData<Data>> {
        return categoryPolyclinicRepository.fetchCategoryPolyclinic()
    }
}