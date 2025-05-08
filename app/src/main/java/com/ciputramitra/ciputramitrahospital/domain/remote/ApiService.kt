package com.ciputramitra.ciputramitrahospital.domain.remote

import com.ciputramitra.ciputramitrahospital.response.Wrapper
import com.ciputramitra.ciputramitrahospital.response.auth.SignResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirm: String,
        @Field("role") role: String,
        @Field("whatsaap") whatsaap: String,
        @Field("kota") kota: String,
        @Field("provinsi") provinsi: String,
        @Field("status_aktif") status_aktif: String,
    ): Wrapper<SignResponse>


    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Wrapper<SignResponse>
}