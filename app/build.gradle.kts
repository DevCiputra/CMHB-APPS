import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.ciputramitra.ciputramitrahospital"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ciputramitra.ciputramitrahospital"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        flavorDimensions("dev")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    productFlavors {
        create("dev") {
            applicationId = "com.ciputramitra.ciputramitrahospital"
            buildConfigField(type = "String", name = "BASE_URL", value = "\"${properties.getProperty("BASE_URL")}\"")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


//    Firebase Messaging
    implementation(libs.firebase.messaging)
    //    Map Composable
    implementation(libs.map.composable)
//    Font
    implementation(libs.font.style)

//    Icon Compose
    api(libs.androidx.material.icons.extended)

//    ViewModel
    implementation(libs.androidx.viewmodel)

//    Koin
    implementation(libs.koin.android)
    implementation(libs.koin.core)

//    Retrofit
    implementation(libs.retrofit.android)
    implementation(libs.retrofit.converter)

//    DataStore
    implementation(libs.data.store)

//    Coroutines
    implementation(libs.corotuines.android)
    implementation(libs.corotuines.core)

//    Okhttp3
    implementation(libs.okhttp3.logging)

//    Lottie Animation
    implementation(libs.lottie.animation)

//    Serialization
    implementation(libs.kotlinx.serialization.json)

//    Paging Compose
    implementation(libs.androidx.paging.compose)

//    Navigation Compose
    implementation(libs.navagiation.compose)

//    Splash Screen
    implementation(libs.splash.api)

//    Coil Compose
    implementation(libs.coil.compose)

//    Service Location
    implementation(libs.service.location)

//    Accompanist Permission
    implementation(libs.accompanist.permissions)

//    MidTrans
    implementation(libs.midtrans.android)

//    Room
    implementation(libs.room.runtime.android)
    ksp(libs.room.runtime.compiler)
    implementation(libs.room.ktx)

//    Firebase Boom
    implementation(platform(libs.firebase.boom))

//    Work Manager untuk handling Background Service
    implementation(libs.work.manager.ktx)

//    Biometric
    implementation(libs.biometric.face)
}