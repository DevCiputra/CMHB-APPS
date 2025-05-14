package com.ciputramitra.ciputramitrahospital.response.categoryPoly


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("category_polyclinic")
    val categoryPolyclinic: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_category_poly")
    val imageCategoryPoly: String,
    @SerializedName("updated_at")
    val updatedAt: String
)