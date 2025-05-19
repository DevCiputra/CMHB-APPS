package com.ciputramitra.ciputramitrahospital.ui.profile

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ciputramitra.ciputramitrahospital.domain.state.ValidationStatement

class ValidationProfile: ViewModel() {
	
	
	var blood by mutableStateOf(
		ValidationStatement(validation = { it.isNotBlank()})
	)
	
	var gender by mutableStateOf(
		ValidationStatement(validation = {it.isNotBlank()})
	)
	
	var ttl by mutableStateOf(
		ValidationStatement(validation = {it.isNotBlank()})
	)
	
	
	fun validateForm(): Boolean {
		blood = blood.copy(showError = !blood.validation(blood.value))
		
		gender = gender.copy(showError = !gender.validation(gender.value))
		
		ttl = ttl.copy(showError = !ttl.validation(ttl.value))
		
		return blood.validation(blood.value) && gender.validation(gender.value) && ttl.validation(ttl.value)
	}
}