package com.ciputramitra.ciputramitrahospital.ui.doctorall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ciputramitra.ciputramitrahospital.domain.data.RequestDoctorAll
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.domain.usecase.DoctorAllUseCase
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorItems
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoctorAllViewModel(
    private val doctorAllUseCase: DoctorAllUseCase
): ViewModel() {
    private val _queryParams = MutableStateFlow(RequestDoctorAll())
    val queryParams : StateFlow<RequestDoctorAll> = _queryParams.asStateFlow()

    private val _doctorDetailState = MutableStateFlow<StateManagement>(StateManagement.Idle)
    val doctorDetailState : StateFlow<StateManagement> = _doctorDetailState.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val doctors : Flow<PagingData<DoctorItems>> = queryParams
        .flatMapLatest { params ->
            doctorAllUseCase.fetchDoctorAll(
                specialName = params.spesialisName ?: "",
                categoryPolyclinicID = params.categoryPolyclinicID ?: 0,
                cheap = params.cheap ?: "",
                expensive = params.expensive ?: "",
                consultationStatus = params.konsultasiStatus ?: "",
                reservationStatus = params.reservasiStatus ?: "",
                statusDoctor = params.statusDokter ?: "",
                userName =  params.userName ?: "",
                today =  params.today ?: "",
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
        userName: String? = null,
        today: String? = null,
    ) {
        _queryParams.update { currentParams ->
            currentParams.copy(
                spesialisName = specialName ?: currentParams.spesialisName,
                categoryPolyclinicID = categoryPolyclinicID ?: currentParams.categoryPolyclinicID,
                cheap = cheap ?: currentParams.cheap,
                expensive = expensive ?: currentParams.expensive,
                konsultasiStatus = consultationStatus ?: currentParams.konsultasiStatus,
                reservasiStatus = reservationStatus ?: currentParams.reservasiStatus,
                statusDokter = statusDoctor ?: currentParams.statusDokter,
                userName = userName ?: currentParams.userName,
                today = today ?: currentParams.today
            )
        }
    }

    fun fetchDoctorDetail(id: Int) {
        viewModelScope.launch {
            _doctorDetailState.value = StateManagement.Loading
            val result = doctorAllUseCase.invoke(id = id)
            result.fold(
                onSuccess = { doctorResponse ->
                    _doctorDetailState.value = StateManagement.DoctorDetailSuccess(doctorDetailResponse = doctorResponse)
                },
                onFailure = { error ->
                    _doctorDetailState.value = StateManagement.Error(error.message ?: "Not Found")
                }
            )
        }
    }

    fun resetFilter() {
        _queryParams.value = RequestDoctorAll()
    }

    fun DoctorAllViewModel.getSelectedDay(): Flow<String?> {
        return this.queryParams.map { it.today }
    }

    fun clearStateDetail() {
        viewModelScope.launch {
            _doctorDetailState.value = StateManagement.Idle
        }
    }
}