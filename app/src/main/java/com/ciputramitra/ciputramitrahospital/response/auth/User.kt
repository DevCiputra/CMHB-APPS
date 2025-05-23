package com.ciputramitra.ciputramitrahospital.response.auth


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("address")
    val address: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("status_aktif")
    val statusAktif: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("whatsaap")
    val whatsaap: String,
    @SerializedName("fcm")
    val fcm: String
)