package com.ciputramitra.ciputramitrahospital.domain.remote

import com.ciputramitra.ciputramitrahospital.response.Wrapper
import com.ciputramitra.ciputramitrahospital.response.auth.SignResponse
import com.ciputramitra.ciputramitrahospital.response.category.CategoryResponse
import com.ciputramitra.ciputramitrahospital.response.categoryPoly.CategoryPolyclinicResponse
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorAllResponse
import com.ciputramitra.ciputramitrahospital.response.doctordetail.DoctorDetailResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirm: String,
        @Field("role") role: String,
        @Field("whatsaap") whatsaap: String,
        @Field("address") address: String,
        @Field("status_aktif") status_aktif: String,
        @Field("fcm") fcm: String
    ): Wrapper<SignResponse>


    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Wrapper<SignResponse>

//    CategoryProduct
    @GET("category")
    suspend fun fetchCategory(): CategoryResponse

    @GET("categoryPoly")
    suspend fun fetchCategoryPolyclinic(
        @Query("page") page: Int,
    ): Wrapper<CategoryPolyclinicResponse>


    @GET("FetchDokterProfile")
    suspend fun fetchDoctorAll(
        @Query("page") page: Int,
        @Query("spesialis_name") specialName: String,
        @Query("category_polyclinic_id") categoryPolyclinicID: Int,
        @Query("cheap") cheap: String,
        @Query("expensive") expensive: String,
        @Query("konsultasi") consultationStatus: String,
        @Query("reservasi") reservationStatus: String,
        @Query("status_dokter") statusDoctor: String,
        @Query("user_name") userName: String,
        @Query("hari") today: String,
    ) : Wrapper<DoctorAllResponse>

    @GET("FetchDokterProfile")
    suspend fun fetchDetailDoctor(
        @Query("id") id: Int
    ): DoctorDetailResponse

}