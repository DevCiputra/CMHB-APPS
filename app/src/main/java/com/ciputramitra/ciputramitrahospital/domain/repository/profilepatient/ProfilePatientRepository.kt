package com.ciputramitra.ciputramitrahospital.domain.repository.profilepatient

import com.ciputramitra.ciputramitrahospital.response.profilepasien.FetchProfilePasienIDResponse
import com.ciputramitra.ciputramitrahospital.response.profilepasien.post.PostProfilePatientResponse

interface ProfilePatientRepository {
	suspend fun fetchProfilePatientUserID(userID: Int): Result<FetchProfilePasienIDResponse>
	suspend fun postProfilePatient(
		userID: Int,
		gender: String,
		golongan_darah: String,
		riwayat_medis: String,
		alergi: String,
		tempatTanggalLahir: String
	): Result<PostProfilePatientResponse>
}