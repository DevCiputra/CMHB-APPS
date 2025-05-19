package com.ciputramitra.ciputramitrahospital.navgraph

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Register


@Serializable
object Authentication

@Serializable
object Home

@Serializable
object ConsultationOnline

@Serializable
data class DoctorAll(
    val categoryPolyclinicID : Int,
    val nameCategoryPolyclinic: String,
)


@Serializable
data class DoctorDetailArgs(
    val doctorID: Int
)

@Serializable
object RequestOTP

@Serializable
data class VerificationRequestOTP(
    val email: String
)