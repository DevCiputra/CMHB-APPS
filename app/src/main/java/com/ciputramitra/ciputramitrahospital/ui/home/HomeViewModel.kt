package com.ciputramitra.ciputramitrahospital.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciputramitra.ciputramitrahospital.domain.remote.HttpClient
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.domain.usecase.HomeUseCase
import com.ciputramitra.ciputramitrahospital.response.category.CategoryResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase,
    private val httpClient: HttpClient
): ViewModel() {
    private val _homeState = MutableStateFlow<StateManagement>(StateManagement.Idle)
    val homeState: StateFlow<StateManagement> = _homeState.asStateFlow()

    private var cacheCategory: CategoryResponse? = null

    fun fetchCategory() {
        viewModelScope.launch {
            _homeState.value = StateManagement.Loading
            val result = homeUseCase.fetchCategory()
            result.fold(
                onSuccess = { categoryResponse ->
                    cacheCategory = categoryResponse
                    combineState()

                    // Rebuild HTTP client agar menggunakan token baru
                    httpClient.rebuildClient() // Inject httpClient ke ViewModel
                },
                onFailure = { error ->
                    _homeState.value = StateManagement.Error(error.message.toString())
                }
            )
        }
    }



    fun combineState() {
        val categories = cacheCategory
        if (categories != null) {
            _homeState.value = StateManagement.HomeSuccess(
                categoryResponse = categories,
            )
        }
    }

    fun clearHomeState() {
        viewModelScope.launch {
            _homeState.value = StateManagement.Idle
        }
    }
}