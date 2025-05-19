package com.ciputramitra.ciputramitrahospital.ui.sign

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Approval
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ciputramitra.ciputramitrahospital.component.FormCheckBox
import com.ciputramitra.ciputramitrahospital.component.FormTextField
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsBold
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit,
) {
    val validationAuth: ValidationsAuth = viewModel()
    val registerState by authViewModel.authState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 12.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title =  {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 22.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = "Create an account",
                            fontFamily = poppinsBold,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )

                        Text(
                            text = "Welcome! Please enter your details",
                            fontFamily = poppinsMedium,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                        )
                    }

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowCircleLeft,
                            contentDescription = null
                        )
                    }
                },
                expandedHeight = TopAppBarDefaults.MediumAppBarExpandedHeight
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding()
                    .verticalScroll(state = rememberScrollState())
            ) {
                FormTextField(
                    value = validationAuth.userName.value,
                    onValueChange = {
                        validationAuth.userName = validationAuth.userName.copy(value = it)
                    },
                    label = "Username",
                    placeholder = "Nama lengkap anda",
                    error = validationAuth.userName.showError,
                    leadingIcon = Icons.Default.VerifiedUser,
                    keyboardType = KeyboardType.Text,
                    singleLine = true,
                )
                FormTextField(
                    value = validationAuth.email.value,
                    onValueChange = {
                        validationAuth.email = validationAuth.email.copy(value = it)
                    },
                    label = "Email",
                    placeholder = "masukan email valid",
                    error = validationAuth.email.showError,
                    leadingIcon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    singleLine = true,
                    suffix = {
                        Text(
                            text = ".com",
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                    }
                )

                FormTextField(
                    value = validationAuth.password.value,
                    onValueChange = {
                        validationAuth.password = validationAuth.password.copy(
                            value = it
                        )
                    },
                    label = "Password",
                    placeholder = "Minimal 6 character",
                    error = validationAuth.password.showError,
                    leadingIcon = Icons.Default.Fingerprint,
                    keyboardType = KeyboardType.Password,
                    isPasswordField = true,
                    isVisible = validationAuth.password.isVisible,
                    onVisibilityChange = {
                        validationAuth.password = validationAuth.password.copy(isVisible = it)
                    },
                    singleLine = true
                )

                FormTextField(
                    value = validationAuth.whatsapp.value,
                    onValueChange = {
                        validationAuth.whatsapp = validationAuth.whatsapp.copy(value = it)
                    },
                    label = "Whatsapp",
                    placeholder = "mulai dari angka 0",
                    error = validationAuth.whatsapp.showError,
                    leadingIcon = Icons.Default.Whatsapp,
                    keyboardType = KeyboardType.Text,
                    singleLine = true,
                )

                FormTextField(
                    value = validationAuth.address.value,
                    onValueChange = {
                        validationAuth.address = validationAuth.address.copy(value = it)
                    },
                    label = "Alamat lengkap",
                    placeholder = "contoh : alamat , kota , provinsi",
                    error = validationAuth.address.showError,
                    leadingIcon = Icons.Default.LocationCity,
                    keyboardType = KeyboardType.Text,
                    singleLine = true,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FormCheckBox(
                        checked = validationAuth.checkBoxChange,
                        onCheckedChange = {
                            validationAuth.checkBoxChange = it
                        },
                        error = validationAuth.showCheckBoxError,
                        label = "Accept terms and conditions"
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    shape = RoundedCornerShape(28.dp),
                    onClick = {
                        FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->

                            if (validationAuth.validateForm()) {
                                authViewModel.register(
                                    name = validationAuth.userName.value,
                                    email = validationAuth.email.value,
                                    password = validationAuth.password.value,
                                    passwordConfirmation = validationAuth.password.value,
                                    role = "Pasien",
                                    whatsaap = validationAuth.whatsapp.value,
                                    address = validationAuth.address.value,
                                    status_aktif = "offline",
                                    fcm = token
                                )
                            }
                        }

                    }
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Register sekarang",
                        fontFamily = poppinsMedium,
                        color = Color.White
                    )
                }

            }

            when(val state = registerState) {
                is StateManagement.Loading -> LoadingLottieAnimation()
                is StateManagement.Error -> LaunchedEffect(key1 = state) {
                    Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                }

                is StateManagement.RegisterSuccess -> LaunchedEffect(key1 = state) {
                    onRegisterSuccess()
                    Toast.makeText(context, "Register berhasil silahkan login", Toast.LENGTH_LONG).show()
                }

                else -> authViewModel.clearAuthState()
            }

        }
    }
}