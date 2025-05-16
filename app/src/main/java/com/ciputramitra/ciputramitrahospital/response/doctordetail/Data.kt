package com.ciputramitra.ciputramitrahospital.response.doctordetail


import com.google.gson.annotations.SerializedName

data class Data(
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
    @SerializedName("jadwals")
    val jadwals: List<Jadwal>,
    @SerializedName("konsultasi")
    val konsultasi: String,
    @SerializedName("link_accuity")
    val linkAccuity: String,
    @SerializedName("medis")
    val medics: List<Medic>,
    @SerializedName("no_str")
    val noStr: String,
    @SerializedName("payment_konsultasi")
    val paymentKonsultasi: Int,
    @SerializedName("payment_strike")
    val paymentStrike: Int,
    @SerializedName("pendidikans")
    val pendidikans: List<Pendidikan>,
    @SerializedName("pengalamans")
    val pengalamans: List<Pengalaman>,
    @SerializedName("reservasi")
    val reservasi: String,
    @SerializedName("spesialis_name")
    val spesialisName: String,
    @SerializedName("status_dokter")
    val statusDokter: String,
    @SerializedName("ulasans")
    val ulasans: List<Ulasans>,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("users")
    val users: Users
)