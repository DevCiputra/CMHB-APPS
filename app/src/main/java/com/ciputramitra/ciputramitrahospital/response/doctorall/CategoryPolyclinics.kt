package com.ciputramitra.ciputramitrahospital.response.doctorall


import com.google.gson.annotations.SerializedName

data class CategoryPolyclinics(
    @SerializedName("category_polyclinic")
    val categoryPolyclinic: String,
    @SerializedName("id")
    val id: Int
)