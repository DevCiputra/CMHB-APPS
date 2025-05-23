package com.ciputramitra.ciputramitrahospital.ui.sign

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ciputramitra.ciputramitrahospital.component.FormCheckBox
import com.ciputramitra.ciputramitrahospital.component.FormTextField
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.navgraph.Register
import com.ciputramitra.ciputramitrahospital.navgraph.RequestOTP
import com.ciputramitra.ciputramitrahospital.ui.theme.greenColor
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsBold
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    
    val validationAuth: ValidationsAuth = viewModel()
    val loginState by authViewModel.authState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 14.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title =  {
                    Column {
                        Text(
                            text = "Welcome back",
                            fontFamily = poppinsBold,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = "Digital health solutions with Ciputra Mitra Hospital's standard of excellence",
                            fontFamily = poppinsMedium,
                            color = Color.Gray,
                            fontSize = 13.sp
                        )
                    }
                },
                expandedHeight = TopAppBarDefaults.LargeAppBarExpandedHeight
            )
        },
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
                    value = validationAuth.email.value,
                    onValueChange = {
                        validationAuth.email = validationAuth.email.copy(value = it)
                    },
                    label = "Email",
                    placeholder = "masukan email terdaftar",
                    error = validationAuth.email.showError,
                    leadingIcon = Icons.Default.AccountCircle,
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
                    placeholder = "masukan password anda",
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FormCheckBox(
                        checked = validationAuth.checkBoxChange,
                        onCheckedChange = {
                            validationAuth.checkBoxChange = it
                        },
                        error = validationAuth.showCheckBoxError,
                        label = "Remember me"
                    )
                    
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                navController.navigate(
                                    route = RequestOTP
                                )
                            },
                        text = "Reset password ?",
                        fontFamily = poppinsMedium,
                        fontSize = 12.sp,
                        color = greenColor,
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    shape = RoundedCornerShape(28.dp),
                    onClick = {
                        if (validationAuth.validateForm()) {
                            authViewModel.login(
                                email = validationAuth.email.value,
                                password = validationAuth.password.value,
                            )
                        }
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Login sekarang",
                        fontFamily = poppinsMedium,
                        color = Color.White
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    HorizontalDivider(
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "Create your account ?",
                            fontFamily = poppinsMedium,
                            fontSize = 12.sp,
                            color = Color.Black
                        )

                        Text(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(
                                        route = Register
                                    )
                                },
                            text = "Sign up",
                            fontFamily = poppinsBold,
                            fontSize = 12.sp,
                            color = greenColor,
                            fontWeight = FontWeight.Medium,
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    }

                    HorizontalDivider(
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                }
                
            }

            when(val state = loginState) {
                is StateManagement.Loading -> LoadingLottieAnimation()
                is StateManagement.Error ->  {
                    LaunchedEffect(key1 = state ) {
                        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is StateManagement.LoginSuccess -> LaunchedEffect(key1 = state) {
                    onLoginSuccess()
                }

                else -> authViewModel.clearAuthState()
            }
        }
    }
}