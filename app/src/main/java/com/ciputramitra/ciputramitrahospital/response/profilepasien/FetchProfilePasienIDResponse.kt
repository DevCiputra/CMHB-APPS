package com.ciputramitra.ciputramitrahospital.response.profilepasien


import com.ciputramitra.ciputramitrahospital.response.Meta
import com.google.gson.annotations.SerializedName

data class FetchProfilePasienIDResponse(
    @SerializedName("data")
    val profilePatientID: ProfilePatientID,
    @SerializedName("meta")
    val meta: Meta
)