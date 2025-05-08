package com.ciputramitra.ciputramitrahospital.di

import com.ciputramitra.ciputramitrahospital.datastore.DataStoreManager
import com.ciputramitra.ciputramitrahospital.domain.remote.HttpClient
import com.ciputramitra.ciputramitrahospital.domain.repository.AuthRepository
import com.ciputramitra.ciputramitrahospital.domain.repository.AuthRepositoryImpl
import com.ciputramitra.ciputramitrahospital.domain.usecase.AuthUseCase
import com.ciputramitra.ciputramitrahospital.ui.sign.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    single { DataStoreManager(androidContext()) }
    single { HttpClient(get()) }
    single { get<HttpClient>().getApi() ?: throw IllegalStateException("API not initialized") }


    //    Authentication
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { AuthUseCase(get()) }
    viewModel { AuthViewModel(get()) }
}