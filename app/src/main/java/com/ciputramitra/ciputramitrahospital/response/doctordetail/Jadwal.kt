package com.ciputramitra.ciputramitrahospital.response.doctordetail
import com.google.gson.annotations.SerializedName

data class Jadwal(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("dokter_profile_id")
    val dokterProfileId: Int,
    @SerializedName("hari")
    val hari: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("jadwal_jam")
    val jadwalJam: String,
    @SerializedName("updated_at")
    val updatedAt: String
)