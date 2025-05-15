package com.ciputramitra.ciputramitrahospital.domain.data

data class RequestDoctorAll(
    val spesialisName: String? = "",
    val categoryPolyclinicID: Int? = 0,
    val cheap: String? = "",
    val expensive: String? = "",
    val konsultasiStatus: String? = "",
    val reservasiStatus: String? = "",
    val statusDokter: String? = ""
)