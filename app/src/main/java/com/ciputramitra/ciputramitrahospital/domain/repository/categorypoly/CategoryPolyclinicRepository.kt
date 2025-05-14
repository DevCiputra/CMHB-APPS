package com.ciputramitra.ciputramitrahospital.domain.repository.categorypoly

import com.ciputramitra.ciputramitrahospital.response.categorypoly.CategoryPolyclinicResponse

interface CategoryPolyclinicRepository {
    suspend fun fetchCategoryPolyclinic(): Result<CategoryPolyclinicResponse>
}