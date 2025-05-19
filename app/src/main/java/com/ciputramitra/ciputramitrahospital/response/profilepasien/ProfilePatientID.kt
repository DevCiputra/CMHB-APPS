package com.ciputramitra.ciputramitrahospital.response.profilepasien


import com.google.gson.annotations.SerializedName

data class ProfilePatientID(
    @SerializedName("alergi")
    val alergi: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("golongan_darah")
    val golonganDarah: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("riwayat_medis")
    val riwayatMedis: String,
    @SerializedName("tempat_tanggal_lahir")
    val tempatTanggalLahir: Any,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("users")
    val users: Users
)