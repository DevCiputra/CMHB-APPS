package com.ciputramitra.ciputramitrahospital.navgraph

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ciputramitra.ciputramitrahospital.component.bottomNavigation
import com.ciputramitra.ciputramitrahospital.ui.consultation.ConsultationPatientOnline
import com.ciputramitra.ciputramitrahospital.ui.consultation.ConsultationViewModel
import com.ciputramitra.ciputramitrahospital.ui.doctorall.DoctorAllScreen
import com.ciputramitra.ciputramitrahospital.ui.doctorall.DoctorAllViewModel
import com.ciputramitra.ciputramitrahospital.ui.doctorall.DoctorDetailScreen
import com.ciputramitra.ciputramitrahospital.ui.home.HomeScreen
import com.ciputramitra.ciputramitrahospital.ui.home.HomeViewModel
import com.ciputramitra.ciputramitrahospital.ui.info.InfoScreen
import com.ciputramitra.ciputramitrahospital.ui.profile.ProfileScreen
import com.ciputramitra.ciputramitrahospital.ui.sign.AuthViewModel
import com.ciputramitra.ciputramitrahospital.ui.sign.AuthenticationFace
import com.ciputramitra.ciputramitrahospital.ui.sign.LoginScreen
import com.ciputramitra.ciputramitrahospital.ui.sign.RegisterScreen
import com.ciputramitra.ciputramitrahospital.ui.sign.ResetPassword
import com.ciputramitra.ciputramitrahospital.ui.sign.VerificationOTP
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium
import com.ciputramitra.ciputramitrahospital.ui.theme.whiteCustom
import com.ciputramitra.ciputramitrahospital.ui.transaction.TransactionScreen

@SuppressLint("NewApi")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavGraph(
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,
    consultationViewModel: ConsultationViewModel,
    doctorAllViewModel: DoctorAllViewModel,
) {

    val isLoggedIn by authViewModel.isLoggedIn.collectAsStateWithLifecycle(initialValue = false)
    val token by authViewModel.token.collectAsStateWithLifecycle()
    val fetchUser by authViewModel.fetchUser.collectAsStateWithLifecycle()

    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(value = 0) }
    val context = LocalContext.current


    NavHost(
        navController =  navController,
        startDestination = if (isLoggedIn && token != null) Home else Login
    ) {
        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    if (token != null)
                        navController.navigate(route = Home) {
                            popUpTo(route = Login) { inclusive = true }
                        }
                },
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable<Authentication> {
            AuthenticationFace(
                navController = navController,
                homeViewModel = homeViewModel
            )
        }

        composable<Register> {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(route = Login) {
                        popUpTo(route = Login) { inclusive = true }
                    }
                },
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable<Home> {
            BackHandler(
                enabled = true
            ) {
                if (selectedItemIndex == 0)
                    (context as Activity).finish()
                else selectedItemIndex = 0
            }

            Scaffold(
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier.shadow(elevation = 20.dp),
                        containerColor = Color.White
                    ) {
                        bottomNavigation().forEachIndexed { index, items ->
                            NavigationBarItem(
                                selected = selectedItemIndex == index,
                                onClick = { selectedItemIndex = index },
                                label = {
                                    Text(
                                        text = items.title,
                                        color = if (index == selectedItemIndex)
                                            items.selectedColor
                                        else items.unSelectedColor,
                                        fontFamily = poppinsMedium,
                                        fontSize = 12.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex)
                                            items.selectedIcon
                                        else items.unSelectedIcon,
                                        tint = if (index == selectedItemIndex)
                                            items.selectedIconColor else items.unSelectedIconColor,
                                        contentDescription = items.title,
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = whiteCustom
                                )
                            )
                        }
                    }
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(paddingValues)
                ) {
                    when (selectedItemIndex) {
                        0 -> HomeScreen(
                            navController = navController,
                            fetchUser = fetchUser,
                            homeViewModel = homeViewModel
                        )

                        1 -> InfoScreen(
                            navController = navController,
                        )

                        2 -> TransactionScreen(
                            navController = navController,
                        )

                        3 -> ProfileScreen(
                            navController = navController
                        )
                    }
                }
            }
        }

        composable<ConsultationOnline> {
            BackHandler {
                navController.navigateUp()
            }

            ConsultationPatientOnline(
                consultationViewModel = consultationViewModel,
                navController = navController,
            )
        }

        composable<DoctorAll> {
            BackHandler {
                navController.navigateUp()
                doctorAllViewModel.resetFilter()
            }

            val args = it.toRoute<DoctorAll>()
            DoctorAllScreen(
                doctorAllViewModel = doctorAllViewModel,
                navController = navController,
                categoryPolyclinicID = args.categoryPolyclinicID,
                nameCategoryPolyclinic = args.nameCategoryPolyclinic,
            )
        }

        composable<DoctorDetailArgs> {
            BackHandler {
                navController.navigateUp()
            }

            val args = it.toRoute<DoctorDetailArgs>()
            DoctorDetailScreen(
                doctorAllViewModel = doctorAllViewModel,
                navController = navController,
                doctorID = args.doctorID
            )
        }
        
        composable<RequestOTP> {
            BackHandler {
                navController.navigateUp()
            }
            
            ResetPassword(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        
        composable<VerificationRequestOTP> {
            BackHandler {
                navController.navigateUp()
            }
            val args = it.toRoute<VerificationRequestOTP>()
            VerificationOTP(
                email = args.email,
                authViewModel = authViewModel,
                navController = navController
            )
        }

    }
}

