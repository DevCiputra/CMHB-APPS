package com.ciputramitra.ciputramitrahospital.ui.sign

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ciputramitra.ciputramitrahospital.domain.state.ValidationStatement

class ValidationResetPassword: ViewModel() {
	var email by mutableStateOf(
		ValidationStatement(validation = { Patterns.EMAIL_ADDRESS.matcher(it).matches() })
	)
	
	var password by mutableStateOf(
		ValidationStatement(validation = { it.length >= 6})
	)
	
	fun validateFormResetPassword(): Boolean {
		email = email.copy(showError = !email.validation(email.value))
		
		password = password.copy(showError = !password.validation(password.value))
		
		return email.validation(email.value)
	}
}