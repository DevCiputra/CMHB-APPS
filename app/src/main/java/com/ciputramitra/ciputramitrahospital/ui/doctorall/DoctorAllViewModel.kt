package com.ciputramitra.ciputramitrahospital.ui.doctorall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ciputramitra.ciputramitrahospital.domain.data.RequestDoctorAll
import com.ciputramitra.ciputramitrahospital.domain.usecase.DoctorAllUseCase
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorAllItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

class DoctorAllViewModel(
    private val doctorAllUseCase: DoctorAllUseCase
): ViewModel() {
    private val _queryParams = MutableStateFlow(RequestDoctorAll())
    private val queryParams : StateFlow<RequestDoctorAll> = _queryParams.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val doctors : Flow<PagingData<DoctorAllItems>> = queryParams
        .flatMapLatest { params ->
            doctorAllUseCase.fetchDoctorAll(
                specialName = params.spesialisName ?: "",
                categoryPolyclinicID = params.categoryPolyclinicID ?: 0,
                cheap = params.cheap ?: "",
                expensive = params.expensive ?: "",
                consultationStatus = params.konsultasiStatus ?: "",
                reservationStatus = params.reservasiStatus ?: "",
                statusDoctor = params.statusDokter ?: "",
            )
        }
        .cachedIn(viewModelScope)

    fun fetchDoctorAll(
        specialName: String? = null,
        categoryPolyclinicID: Int? = null,
        cheap: String? = null,
        expensive: String? = null,
        consultationStatus: String? = null,
        reservationStatus: String? = null,
        statusDoctor: String? = null,
    ) {
        _queryParams.update { currentParams ->
            currentParams.copy(
                spesialisName = specialName ?: currentParams.spesialisName,
                categoryPolyclinicID = categoryPolyclinicID ?: currentParams.categoryPolyclinicID,
                cheap = cheap ?: currentParams.cheap,
                expensive = expensive ?: currentParams.expensive,
                konsultasiStatus = consultationStatus ?: currentParams.konsultasiStatus,
                reservasiStatus = reservationStatus ?: currentParams.reservasiStatus,
                statusDokter = statusDoctor ?: currentParams.statusDokter
            )
        }
    }
}