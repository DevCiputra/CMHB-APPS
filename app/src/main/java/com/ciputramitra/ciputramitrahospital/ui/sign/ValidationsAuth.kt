package com.ciputramitra.ciputramitrahospital.ui.sign

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ciputramitra.ciputramitrahospital.domain.state.ValidationStatement

class ValidationsAuth: ViewModel() {
    var email by mutableStateOf(
        ValidationStatement(validation = { Patterns.EMAIL_ADDRESS.matcher(it).matches() })
    )

    var password by mutableStateOf(
        ValidationStatement(validation = { it.length >= 6})
    )

    var passwordNew by mutableStateOf(
        ValidationStatement(validation = {true})
    )

    var userName by mutableStateOf(
        ValidationStatement(validation = { it.isNotBlank()})
    )

    var phone by mutableStateOf(
        ValidationStatement(validation = { it.isNotBlank()})
    )

    var checkBoxChange by mutableStateOf(false)
    var showCheckBoxError by mutableStateOf(false)

    fun validateForm(): Boolean {
        email = email.copy(showError = !email.validation(email.value))

        password = password.copy(showError = !password.validation(password.value))

        passwordNew = passwordNew.copy(showError = !passwordNew.validation(passwordNew.value))

        userName = userName.copy(showError = !userName.validation(userName.value))

        phone = phone.copy(showError = !phone.validation(phone.value))

        showCheckBoxError = !checkBoxChange

        return checkBoxChange &&
                userName.validation(userName.value)
    }
}