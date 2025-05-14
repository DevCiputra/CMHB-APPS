package com.ciputramitra.ciputramitrahospital.domain.repository.categorypoly

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.categoryPoly.Data
import kotlinx.coroutines.flow.Flow

class CategoryPolyclinicRepositoryImpl(
    private val apiService: ApiService
): CategoryPolyclinicRepository {
    override suspend fun fetchCategoryPolyclinic(): Flow<PagingData<Data>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 50
        ),
        pagingSourceFactory = {
            PagingSourcePolyclinic(
                apiService = apiService
            )
        }
    ).flow

}