package com.ciputramitra.ciputramitrahospital.response.doctorall


import com.google.gson.annotations.SerializedName

data class DoctorItems(
    @SerializedName("category_polyclinic_id")
    val categoryPolyclinicId: Int,
    @SerializedName("category_polyclinics")
    val categoryPolyclinics: CategoryPolyclinics,
    @SerializedName("id")
    val id: Int,
    @SerializedName("konsultasi")
    val konsultasi: String,
    @SerializedName("link_accuity")
    val linkAccuity: String,
    @SerializedName("reservasi")
    val reservasi: String,
    @SerializedName("spesialis_name")
    val spesialisName: String,
    @SerializedName("status_dokter")
    val statusDokter: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("users")
    val users: Users
)