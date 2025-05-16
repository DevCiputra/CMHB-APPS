package com.ciputramitra.ciputramitrahospital.response.doctorall


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String
)