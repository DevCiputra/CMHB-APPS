package com.ciputramitra.ciputramitrahospital

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ciputramitra.ciputramitrahospital.navgraph.NavGraph
import com.ciputramitra.ciputramitrahospital.ui.consultation.ConsultationViewModel
import com.ciputramitra.ciputramitrahospital.ui.doctorall.DoctorAllViewModel
import com.ciputramitra.ciputramitrahospital.ui.home.HomeViewModel
import com.ciputramitra.ciputramitrahospital.ui.profile.ProfilePatientViewModel
import com.ciputramitra.ciputramitrahospital.ui.sign.AuthViewModel
import com.ciputramitra.ciputramitrahospital.ui.theme.greenColor
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val splashViewModel by viewModels<SplashViewModel>()
    private val authViewModel: AuthViewModel by viewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val doctorAllViewModel: DoctorAllViewModel by viewModel()
    private val consultationViewModel: ConsultationViewModel by viewModel()
    private val profilePatientViewModel: ProfilePatientViewModel by viewModel()
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !splashViewModel.isReady.value
            }
        }
        setContent {
            requestNotificationPermission()
            NavGraph(
                authViewModel = authViewModel,
                homeViewModel = homeViewModel,
                consultationViewModel = consultationViewModel,
                doctorAllViewModel = doctorAllViewModel,
                profilePatientViewModel = profilePatientViewModel
            )
        }
    }

    private fun requestNotificationPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if(!hasPermission) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }
}

@Composable
fun AuthenticatedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Secure Area",
            fontSize = 24.sp,
            color = greenColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "You have successfully authenticated with biometrics!",
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            fontSize = 16.sp
        )
    }
}
