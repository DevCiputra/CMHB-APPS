package com.ciputramitra.ciputramitrahospital.fcm

import retrofit2.http.Body
import retrofit2.http.POST

interface FcmApi {

    @POST("/send")
    suspend fun sendMessage(
        @Body body: SendMessageDto
    )
    @POST("/broadcast")
    suspend fun broadcast(
        @Body body: SendMessageDto
    )
}