package com.ciputramitra.ciputramitrahospital.domain.repository.doctorall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorAllItems
import kotlinx.coroutines.flow.Flow

class DoctorAllRepositoryImpl(
    private val apiService: ApiService
):DoctorAllRepository {
    override suspend fun fetchDoctorAll(
        specialName: String,
        categoryPolyclinicID: Int,
        cheap: String,
        expensive: String,
        consultationStatus: String,
        reservationStatus: String,
        statusDoctor: String
    ): Flow<PagingData<DoctorAllItems>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                maxSize = 50
            ),
            pagingSourceFactory = {
                PagingDoctorAll(
                    apiService, specialName, categoryPolyclinicID, cheap, expensive, consultationStatus, reservationStatus, statusDoctor
                )
            }
        ).flow
}