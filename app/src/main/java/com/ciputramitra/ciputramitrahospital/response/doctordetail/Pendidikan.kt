package com.ciputramitra.ciputramitrahospital.response.doctordetail


import com.google.gson.annotations.SerializedName

data class Pendidikan(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("dokter_profile_id")
    val dokterProfileId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama_riwayat_pendidikan")
    val namaRiwayatPendidikan: String,
    @SerializedName("updated_at")
    val updatedAt: String
)