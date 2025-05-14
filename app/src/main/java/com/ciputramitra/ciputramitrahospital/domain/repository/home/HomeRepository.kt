package com.ciputramitra.ciputramitrahospital.domain.repository.home

import com.ciputramitra.ciputramitrahospital.response.category.CategoryResponse

interface HomeRepository {
    suspend fun fetchCategory(): Result<CategoryResponse>
}