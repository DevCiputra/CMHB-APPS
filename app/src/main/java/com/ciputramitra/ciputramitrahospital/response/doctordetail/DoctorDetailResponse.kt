package com.ciputramitra.ciputramitrahospital.response.doctordetail


import com.ciputramitra.ciputramitrahospital.response.Meta
import com.google.gson.annotations.SerializedName

data class DoctorDetailResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("meta")
    val meta: Meta
)