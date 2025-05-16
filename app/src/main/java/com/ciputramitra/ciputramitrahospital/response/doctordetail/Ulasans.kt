package com.ciputramitra.ciputramitrahospital.response.doctordetail

import com.google.gson.annotations.SerializedName

data class Ulasans(
	@SerializedName("id")
	val id: Int,
	@SerializedName("dokter_profile_id")
	val dokterProfileId: Int,
	@SerializedName("nama_pasien")
	val namePatient: String,
	@SerializedName("rating")
	val rating: Int,
	@SerializedName("created_at")
	val createdAt: String,
	@SerializedName("updated_at")
	val updatedAt: String
)
