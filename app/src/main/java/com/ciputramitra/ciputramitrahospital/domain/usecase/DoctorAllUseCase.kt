package com.ciputramitra.ciputramitrahospital.domain.usecase

import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.domain.repository.doctorall.DoctorAllRepository
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorAllItems
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
        statusDoctor: String
    ): Flow<PagingData<DoctorAllItems>> {
        return doctorAllRepository.fetchDoctorAll(
           specialName, categoryPolyclinicID, cheap, expensive, consultationStatus, reservationStatus, statusDoctor
        )
    }
}