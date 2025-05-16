package com.ciputramitra.ciputramitrahospital.domain.usecase

import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.domain.repository.doctorall.DoctorAllRepository
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorItems
import com.ciputramitra.ciputramitrahospital.response.doctordetail.DoctorDetailResponse
import kotlinx.coroutines.flow.Flow

class DoctorAllUseCase(
    private val doctorAllRepository: DoctorAllRepository
) {
    suspend fun fetchDoctorAll(
        specialName : String,
        categoryPolyclinicID: Int,
        cheap: String,
        expensive: String,
        consultationStatus: String,
        reservationStatus: String,
        statusDoctor: String,
        userName: String,
        today: String,
    ): Flow<PagingData<DoctorItems>> {
        return doctorAllRepository.fetchDoctorAll(
           specialName, categoryPolyclinicID, cheap, expensive, consultationStatus, reservationStatus, statusDoctor, userName, today
        )
    }

    suspend operator fun invoke(id: Int): Result<DoctorDetailResponse> {
        return doctorAllRepository.fetchDoctorDetail(id)
    }
}