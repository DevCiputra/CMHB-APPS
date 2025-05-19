package com.ciputramitra.ciputramitrahospital.di

import com.ciputramitra.ciputramitrahospital.datastore.DataStoreManager
import com.ciputramitra.ciputramitrahospital.domain.remote.HttpClient
import com.ciputramitra.ciputramitrahospital.domain.repository.AuthRepository
import com.ciputramitra.ciputramitrahospital.domain.repository.AuthRepositoryImpl
import com.ciputramitra.ciputramitrahospital.domain.repository.categorypoly.CategoryPolyclinicRepository
import com.ciputramitra.ciputramitrahospital.domain.repository.categorypoly.CategoryPolyclinicRepositoryImpl
import com.ciputramitra.ciputramitrahospital.domain.repository.doctorall.DoctorAllRepository
import com.ciputramitra.ciputramitrahospital.domain.repository.doctorall.DoctorAllRepositoryImpl
import com.ciputramitra.ciputramitrahospital.domain.repository.home.HomeRepository
import com.ciputramitra.ciputramitrahospital.domain.repository.home.HomeRepositoryImpl
import com.ciputramitra.ciputramitrahospital.domain.repository.profilepatient.ProfilePatientRepository
import com.ciputramitra.ciputramitrahospital.domain.repository.profilepatient.ProfilePatientRepositoryImpl
import com.ciputramitra.ciputramitrahospital.domain.usecase.AuthUseCase
import com.ciputramitra.ciputramitrahospital.domain.usecase.CategoryPolyclinicUseCase
import com.ciputramitra.ciputramitrahospital.domain.usecase.DoctorAllUseCase
import com.ciputramitra.ciputramitrahospital.domain.usecase.HomeUseCase
import com.ciputramitra.ciputramitrahospital.domain.usecase.ProfilePatientUseCase
import com.ciputramitra.ciputramitrahospital.ui.consultation.ConsultationViewModel
import com.ciputramitra.ciputramitrahospital.ui.doctorall.DoctorAllViewModel
import com.ciputramitra.ciputramitrahospital.ui.home.HomeViewModel
import com.ciputramitra.ciputramitrahospital.ui.profile.ProfilePatientViewModel
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
    viewModel { AuthViewModel(get(), get()) }

//    Home
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    factory { HomeUseCase(get()) }
    viewModel { HomeViewModel(get(), get()) }


//    CategoryPolyclinic
    single<CategoryPolyclinicRepository> { CategoryPolyclinicRepositoryImpl(get()) }
    factory { CategoryPolyclinicUseCase(get()) }
    viewModel { ConsultationViewModel(get()) }

//    DoctorALL
    single<DoctorAllRepository> { DoctorAllRepositoryImpl(get()) }
    factory { DoctorAllUseCase(get()) }
    viewModel { DoctorAllViewModel(get())}
    
//    Profile Patient
    single<ProfilePatientRepository> { ProfilePatientRepositoryImpl(get()) }
    factory { ProfilePatientUseCase(get()) }
    viewModel { ProfilePatientViewModel(get(), get()) }

}