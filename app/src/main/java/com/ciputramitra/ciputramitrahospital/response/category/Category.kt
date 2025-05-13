package com.ciputramitra.ciputramitrahospital.response.category


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_category")
    val imageCategory: String,
    @SerializedName("name_category")
    val nameCategory: String,
    @SerializedName("updated_at")
    val updatedAt: String
)