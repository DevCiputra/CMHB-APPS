package com.ciputramitra.ciputramitrahospital.response.verificationotp


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("message")
    val message: String
)