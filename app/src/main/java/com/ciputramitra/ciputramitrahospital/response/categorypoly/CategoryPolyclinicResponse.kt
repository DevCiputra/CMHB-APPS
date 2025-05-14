package com.ciputramitra.ciputramitrahospital.response.categorypoly


import com.ciputramitra.ciputramitrahospital.response.Meta
import com.google.gson.annotations.SerializedName

data class CategoryPolyclinicResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("meta")
    val meta: Meta
)