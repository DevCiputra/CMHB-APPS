package com.ciputramitra.ciputramitrahospital.response.profilepasien


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("address")
    val address: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @SerializedName("fcm")
    val fcm: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("status_aktif")
    val statusAktif: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("whatsaap")
    val whatsaap: String
)