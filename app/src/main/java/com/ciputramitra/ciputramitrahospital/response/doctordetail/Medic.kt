package com.ciputramitra.ciputramitrahospital.response.doctordetail


import com.google.gson.annotations.SerializedName

data class Medic(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("dokter_profile_id")
    val dokterProfileId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama_tindakan_medis")
    val namaTindakanMedis: String,
    @SerializedName("updated_at")
    val updatedAt: String
)