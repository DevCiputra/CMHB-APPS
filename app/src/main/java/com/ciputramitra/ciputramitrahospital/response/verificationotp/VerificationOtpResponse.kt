package com.ciputramitra.ciputramitrahospital.response.verificationotp


import com.ciputramitra.ciputramitrahospital.response.Meta
import com.google.gson.annotations.SerializedName

data class VerificationOtpResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("meta")
    val meta: Meta
)