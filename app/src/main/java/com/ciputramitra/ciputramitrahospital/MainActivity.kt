package com.ciputramitra.ciputramitrahospital

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ciputramitra.ciputramitrahospital.navgraph.NavGraph
import com.ciputramitra.ciputramitrahospital.ui.sign.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val splashViewModel by viewModels<SplashViewModel>()
    private val authViewModel: AuthViewModel by viewModel()
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !splashViewModel.isReady.value
            }
        }
        setContent {
            NavGraph(
                authViewModel = authViewModel
            )
        }
    }
}
