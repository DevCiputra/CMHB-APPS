package com.ciputramitra.ciputramitrahospital.domain.usecase

import com.ciputramitra.ciputramitrahospital.domain.repository.profilepatient.ProfilePatientRepository
import com.ciputramitra.ciputramitrahospital.response.profilepasien.FetchProfilePasienIDResponse
import com.ciputramitra.ciputramitrahospital.response.profilepasien.post.PostProfilePatientResponse

class ProfilePatientUseCase(
	private val profilePatientRepository: ProfilePatientRepository
) {
	suspend fun fetchProfilePatientUserID(userID: Int): Result<FetchProfilePasienIDResponse> {
		return profilePatientRepository.fetchProfilePatientUserID(userID = userID)
	}
	
	suspend fun postProfilePatient(userID: Int, gender: String, golongan_darah: String, riwayat_medis: String, alergi: String, tempatTanggalLahir: String): Result<PostProfilePatientResponse> {
		return profilePatientRepository.postProfilePatient(
			userID, gender, golongan_darah, riwayat_medis, alergi, tempatTanggalLahir
		)
	}
}