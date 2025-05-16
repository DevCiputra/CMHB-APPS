package com.ciputramitra.ciputramitrahospital.domain.repository.doctorall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorItems
import com.ciputramitra.ciputramitrahospital.response.doctordetail.DoctorDetailResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

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
        statusDoctor: String,
        userName: String,
        today: String,
    ): Flow<PagingData<DoctorItems>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                maxSize = 50
            ),
            pagingSourceFactory = {
                PagingDoctorAll(
                    apiService, specialName, categoryPolyclinicID, cheap, expensive, consultationStatus, reservationStatus, statusDoctor, userName, today
                )
            }
        ).flow

    override suspend fun fetchDoctorDetail(id: Int): Result<DoctorDetailResponse> {
        return try {
            val response = apiService.fetchDetailDoctor(id = id)
            when(response.meta.code == 200) {
                true -> Result.success(value = response)
                false -> Result.failure(exception = Exception(response.meta.message))
            }
        } catch (e: Exception) {
            when {
                e is HttpException && e.code() == 500 -> {
                    Result.failure(exception =  Exception("Server sedang sibuk"))
                }

                else -> Result.failure(exception = e)
            }
        }
    }
}