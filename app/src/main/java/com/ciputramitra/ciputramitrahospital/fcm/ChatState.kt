package com.ciputramitra.ciputramitrahospital.fcm

data class ChatState(
    val isEnteringToken: Boolean = true,
    val remoteToken: String = "",
    val messageText: String = ""
)