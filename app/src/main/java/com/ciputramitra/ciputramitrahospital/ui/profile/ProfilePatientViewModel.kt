package com.ciputramitra.ciputramitrahospital.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciputramitra.ciputramitrahospital.domain.remote.HttpClient
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.domain.usecase.ProfilePatientUseCase
import com.ciputramitra.ciputramitrahospital.response.profilepasien.ProfilePatientID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfilePatientViewModel(
	private val profilePatientUseCase: ProfilePatientUseCase,
	private val httpClient: HttpClient
) : ViewModel()
{
	private val _fetchProfilePatientUserID = MutableStateFlow<ProfilePatientID?>(value = null)
	val fetchProfilePatientUserID : StateFlow<ProfilePatientID?> = _fetchProfilePatientUserID.asStateFlow()
	
	private val _profilePatientState = MutableStateFlow<StateManagement>(StateManagement.Idle)
	val profilePatientState : StateFlow<StateManagement> = _profilePatientState.asStateFlow()
	
	
	private val _error = MutableStateFlow<String?>(null)
	val error: StateFlow<String?> = _error.asStateFlow()
	
	fun fetchProfilePatientByUserID(userID: Int) {
		viewModelScope.launch {
			
			val result = profilePatientUseCase.fetchProfilePatientUserID(userID = userID)
			result.fold(
				onSuccess = { profileResponse ->
					_fetchProfilePatientUserID.value = profileResponse.profilePatientID
					
					// Rebuild HTTP client agar menggunakan token baru
					httpClient.rebuildClient() // Inject httpClient ke ViewModel
				},
				onFailure = { error ->
					_error.value = error.message ?: "Unknown error"
				}
			)
		}
	}
	
	fun postProfilePatient(
		userID: Int,
		gender: String,
		golongan_darah: String,
		riwayat_medis: String,
		alergi: String,
		tempatTanggalLahir: String
	) {
		viewModelScope.launch {
			_profilePatientState.value = StateManagement.Loading
			val result = profilePatientUseCase.postProfilePatient(userID, gender, golongan_darah, riwayat_medis, alergi, tempatTanggalLahir)
			result.fold(
				onSuccess = { profilePatientResponse ->
					
					_profilePatientState.value = StateManagement.PostProfilePatientSuccess(
						postProfilePatientResponse = profilePatientResponse
					)
					
					// Rebuild HTTP client agar menggunakan token baru
					httpClient.rebuildClient() // Inject httpClient ke ViewModel
					
				},
				onFailure = { error ->
					_profilePatientState.value = StateManagement.Error(error.message.toString())
				}
			)
		}
	}
	
	fun clearProfilePatient() {
		viewModelScope.launch {
			_fetchProfilePatientUserID.value = null
			_error.value = null
		}
	}
}