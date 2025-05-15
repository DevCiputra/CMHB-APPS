package com.ciputramitra.ciputramitrahospital.domain.repository.doctorall

import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorAllItems
import kotlinx.coroutines.flow.Flow

interface DoctorAllRepository {
    suspend fun fetchDoctorAll(
        specialName: String,
        categoryPolyclinicID: Int,
        cheap: String,
        expensive: String,
        consultationStatus: String,
        reservationStatus: String,
        statusDoctor: String,
    ): Flow<PagingData<DoctorAllItems>>
}