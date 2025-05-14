package com.ciputramitra.ciputramitrahospital.domain.repository.categorypoly

import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.response.categoryPoly.Data
import kotlinx.coroutines.flow.Flow


interface CategoryPolyclinicRepository {
    suspend fun fetchCategoryPolyclinic(): Flow<PagingData<Data>>
}