package com.ciputramitra.ciputramitrahospital.domain.repository.doctorall

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorItems

class PagingDoctorAll(
    private val apiService: ApiService,
    private val specialName: String,
    private val categoryPolyclinicID: Int,
    private val cheap: String,
    private val expensive: String,
    private val consultationStatus: String,
    private val reservationStatus: String,
    private val statusDoctor: String,
    private val userName: String,
    private val today: String,
) : PagingSource<Int, DoctorItems>(){
    override fun getRefreshKey(state: PagingState<Int, DoctorItems>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DoctorItems> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.fetchDoctorAll(
                page = currentPage,
                specialName = specialName,
                categoryPolyclinicID = categoryPolyclinicID,
                cheap = cheap,
                expensive = expensive,
                consultationStatus = consultationStatus,
                reservationStatus = reservationStatus,
                statusDoctor = statusDoctor,
                userName = userName,
                today = today
            ).data

            val data = response?.data ?: emptyList()
            val prevKey = if (response?.prevPageUrl == null) null else currentPage -1
            val nextKey = if (response?.nextPageUrl == null) null else currentPage +1

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }
        catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

}