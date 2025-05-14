package com.ciputramitra.ciputramitrahospital.ui.consultation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.domain.usecase.CategoryPolyclinicUseCase
import com.ciputramitra.ciputramitrahospital.response.categorypoly.CategoryPolyclinicResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConsultationViewModel(
    private val categoryPolyclinicUseCase: CategoryPolyclinicUseCase
): ViewModel() {

    private val _categoryPolyclinic = MutableStateFlow<StateManagement>(StateManagement.Idle)
    val categoryPolyclinic: StateFlow<StateManagement> = _categoryPolyclinic.asStateFlow()

    private var cacheCategoryPolyclinic: CategoryPolyclinicResponse? = null

    fun fetchCategoryPolyclinic() {
        viewModelScope.launch {
            _categoryPolyclinic.value = StateManagement.Loading
            val result = categoryPolyclinicUseCase.fetchCategoryPolyclinic()
            result.fold(
                onSuccess = { categoryPolyclinicResponse ->
                    cacheCategoryPolyclinic = categoryPolyclinicResponse
                    combineState()
                },
                onFailure = { error ->
                    _categoryPolyclinic.value = StateManagement.Error(error.message.toString())
                }
            )
        }
    }

    private fun combineState() {
        val categoriesPolyclinic = cacheCategoryPolyclinic
        if (categoriesPolyclinic != null) {
            _categoryPolyclinic.value = StateManagement.CategoryPolyclinicSuccess(
                categoryPolyclinicResponse = categoriesPolyclinic
            )
        }
    }

    fun clearCategoryPolyclinicState() {
        viewModelScope.launch {
            _categoryPolyclinic.value = StateManagement.Idle
        }
    }
}