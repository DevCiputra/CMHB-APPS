package com.ciputramitra.ciputramitrahospital.domain.state

import com.ciputramitra.ciputramitrahospital.response.auth.SignResponse
import com.ciputramitra.ciputramitrahospital.response.auth.User
import com.ciputramitra.ciputramitrahospital.response.category.CategoryResponse
import com.ciputramitra.ciputramitrahospital.response.doctordetail.DoctorDetailResponse
import com.ciputramitra.ciputramitrahospital.response.requestotp.RequestOtpResponse
import com.ciputramitra.ciputramitrahospital.response.verificationotp.VerificationOtpResponse

sealed class StateManagement {
    data object Idle: StateManagement()
    data object Loading: StateManagement()
    data class Error(val message: String): StateManagement()


    //    Authentication
    data class LoginSuccess(val signResponse: SignResponse): StateManagement()
    data class RegisterSuccess(val signResponse: SignResponse): StateManagement()
    data class UpdateUserSuccess(val user : User): StateManagement()
    data class RequestOtpSuccess(val requestOtpResponse: RequestOtpResponse) : StateManagement()
    data class VerificationOtpSuccess(val verificationOtpResponse: VerificationOtpResponse) : StateManagement()

//    Home
    data class HomeSuccess(
        val categoryResponse: CategoryResponse
    ): StateManagement()

// Detail Doctor
    data class DoctorDetailSuccess(
        val doctorDetailResponse: DoctorDetailResponse
    ): StateManagement()
}