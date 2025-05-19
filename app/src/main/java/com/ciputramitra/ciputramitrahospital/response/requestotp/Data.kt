package com.ciputramitra.ciputramitrahospital.response.requestotp


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("message")
    val message: String
)