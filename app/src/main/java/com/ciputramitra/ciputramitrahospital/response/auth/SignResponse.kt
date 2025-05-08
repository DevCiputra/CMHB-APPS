package com.ciputramitra.ciputramitrahospital.response.auth


import com.google.gson.annotations.SerializedName

data class SignResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("user")
    val user: User
)