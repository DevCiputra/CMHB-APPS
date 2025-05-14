package com.ciputramitra.ciputramitrahospital.ui.sign

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.navgraph.Home
import com.ciputramitra.ciputramitrahospital.ui.home.HomeViewModel

@Composable
fun AuthenticationFace(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.
            clickable {
                navController.navigate(route =  Home)
            },
            text = "Tekan Tombol"
        )
    }
}