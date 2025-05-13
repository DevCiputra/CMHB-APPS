package com.ciputramitra.ciputramitrahospital.response.category


import com.ciputramitra.ciputramitrahospital.response.Meta
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    val `data`: List<Category>,
    @SerializedName("meta")
    val meta: Meta
)