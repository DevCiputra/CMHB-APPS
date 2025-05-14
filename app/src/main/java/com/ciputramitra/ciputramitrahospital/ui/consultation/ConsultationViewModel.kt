package com.ciputramitra.ciputramitrahospital.ui.consultation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ciputramitra.ciputramitrahospital.domain.usecase.CategoryPolyclinicUseCase
import com.ciputramitra.ciputramitrahospital.response.categoryPoly.Data
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

class ConsultationViewModel(
    private val categoryPolyclinicUseCase: CategoryPolyclinicUseCase
): ViewModel() {

    private val _queryParams = MutableStateFlow("")
    private val queryParams : StateFlow<String> = _queryParams.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val categoryPolyclinic : Flow<PagingData<Data>> = queryParams
        .flatMapLatest { params ->
            categoryPolyclinicUseCase.fetchCategoryPolyclinic()
        }
        .cachedIn(viewModelScope)

    fun fetchCategoryPolyclinic() {
        _queryParams.update { currentParams ->
            currentParams
        }
    }

}