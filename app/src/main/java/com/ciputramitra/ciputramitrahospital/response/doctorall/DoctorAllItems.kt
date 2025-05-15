package com.ciputramitra.ciputramitrahospital.response.doctorall


import com.google.gson.annotations.SerializedName

data class DoctorAllItems(
    @SerializedName("biografi")
    val biografi: String,
    @SerializedName("category_polyclinic_id")
    val categoryPolyclinicId: Int,
    @SerializedName("category_polyclinics")
    val categoryPolyclinics: CategoryPolyclinics,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("cv_dokter")
    val cvDokter: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("konsultasi")
    val konsultasi: String,
    @SerializedName("link_accuity")
    val linkAccuity: String,
    @SerializedName("no_str")
    val noStr: String,
    @SerializedName("payment_konsultasi")
    val paymentKonsultasi: Int,
    @SerializedName("payment_strike")
    val paymentStrike: Int,
    @SerializedName("reservasi")
    val reservasi: String,
    @SerializedName("spesialis_name")
    val spesialisName: String,
    @SerializedName("status_dokter")
    val statusDokter: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("users")
    val users: Users
)