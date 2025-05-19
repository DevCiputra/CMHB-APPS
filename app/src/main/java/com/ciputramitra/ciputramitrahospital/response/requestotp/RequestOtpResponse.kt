package com.ciputramitra.ciputramitrahospital.response.requestotp


import com.ciputramitra.ciputramitrahospital.response.Meta
import com.google.gson.annotations.SerializedName

data class RequestOtpResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("meta")
    val meta: Meta
)