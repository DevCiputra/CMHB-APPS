package com.ciputramitra.ciputramitrahospital.domain.repository.doctorall

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorAllItems

class PagingDoctorAll(
    private val apiService: ApiService,
    private val specialName: String,
    private val categoryPolyclinicID: Int,
    private val cheap: String,
    private val expensive: String,
    private val consultationStatus: String,
    private val reservationStatus: String,
    private val statusDoctor: String,
) : PagingSource<Int, DoctorAllItems>(){
    override fun getRefreshKey(state: PagingState<Int, DoctorAllItems>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DoctorAllItems> {
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
                statusDoctor = statusDoctor
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