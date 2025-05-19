package com.ciputramitra.ciputramitrahospital.domain.repository.profilepatient

import com.ciputramitra.ciputramitrahospital.domain.remote.ApiService
import com.ciputramitra.ciputramitrahospital.response.profilepasien.FetchProfilePasienIDResponse
import com.ciputramitra.ciputramitrahospital.response.profilepasien.post.PostProfilePatientResponse
import retrofit2.HttpException

class ProfilePatientRepositoryImpl(
	private val apiService: ApiService
): ProfilePatientRepository {
	override suspend fun fetchProfilePatientUserID(userID: Int): Result<FetchProfilePasienIDResponse> {
		return try {
			val response = apiService.fetchProfileByUserID(userID = userID)
			when(response.meta.code == 200) {
				true -> Result.success(value = response)
				false -> Result.failure(exception = Exception(response.meta.message))
			}
		}
		catch (e: Exception) {
			when {
				e is HttpException && e.code() == 500 -> {
					Result.failure(exception =  Exception("Server sedang sibuk"))
				}
				
				e is HttpException && e.code() == 404 -> {
					Result.failure(exception =  Exception("Profile tidak ditemukan untuk user ini"))
				}
				
				else -> Result.failure(exception = e)
			}
		}
	}
	
	override suspend fun postProfilePatient(
		userID: Int,
		gender: String,
		golongan_darah: String,
		riwayat_medis: String,
		alergi: String,
		tempatTanggalLahir: String
	): Result<PostProfilePatientResponse> {
		return try {
			val response = apiService.PostProfilePatient(
				userID, gender, golongan_darah, riwayat_medis, alergi, tempatTanggalLahir
			)
			when(response.meta?.code == 200 && response.data != null) {
				true -> Result.success(value = response.data)
				false -> Result.failure(exception = Exception(response.meta?.message))
			}
		}
		catch (e: Exception) {
			when {
				e is HttpException && e.code() == 500 -> {
					Result.failure(exception =  Exception("Server sedang sibuk"))
				}
				
				e is HttpException && e.code() == 422 -> {
					Result.failure(exception =  Exception("User ini sudah memiliki profile. Tidak dapat membuat profile baru untuk user yang sama."))
				}
				else -> Result.failure(exception = e)
			}
		}
	}
}