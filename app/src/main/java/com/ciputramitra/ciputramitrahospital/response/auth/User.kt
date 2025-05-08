package com.ciputramitra.ciputramitrahospital.response.auth


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("role")
    val role: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("profile_picture")
    val profilePicture:String,
    @SerializedName("whatsapp")
    val whatsapp: String,
    @SerializedName("email_verified_at")
    val emailVerify: String,
    @SerializedName("deleted_at")
    val deletedAt: String
)