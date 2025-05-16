package com.ciputramitra.ciputramitrahospital.domain.repository.doctorall

import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorAllResponse
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorItems
import com.ciputramitra.ciputramitrahospital.response.doctordetail.DoctorDetailResponse
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
        userName: String,
        today: String,
    ): Flow<PagingData<DoctorItems>>

    suspend fun fetchDoctorDetail(id: Int): Result<DoctorDetailResponse>
}