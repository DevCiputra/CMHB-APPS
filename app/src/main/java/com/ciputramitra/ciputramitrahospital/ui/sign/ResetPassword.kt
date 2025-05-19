package com.ciputramitra.ciputramitrahospital.ui.sign

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.MarkEmailUnread
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ciputramitra.ciputramitrahospital.R
import com.ciputramitra.ciputramitrahospital.component.FormTextField
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.navgraph.VerificationRequestOTP
import com.ciputramitra.ciputramitrahospital.ui.theme.greenColor
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsBold
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium

@Composable
fun ResetPassword(
	authViewModel: AuthViewModel,
	navController: NavController
) {
	
	val validationResetPassword: ValidationResetPassword = viewModel()
	val requestOtp by authViewModel.authState.collectAsStateWithLifecycle()
	val context = LocalContext.current
	
	Box(
		modifier = Modifier
			.background(color = Color.White)
			.fillMaxSize(),
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(color = Color.White)
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			AsyncImage(
				model = ImageRequest.Builder(context = LocalContext.current)
					.data(R.drawable.otp)
					.error(R.drawable.logo)
					.build(),
				contentDescription = null,
				modifier = Modifier
					.size(70.dp)
					.clip(shape = RoundedCornerShape(8.dp)),
				contentScale = ContentScale.Crop
			)
			
			Text(
				modifier = Modifier.padding(top = 7.dp),
				text = "VERIFIKASI EMAIL",
				fontFamily = poppinsBold,
				fontSize = 30.sp,
				fontWeight = FontWeight.Bold,
				color = Color.Black,
				textAlign = TextAlign.Center
			)
			
			Text(
				text = "masukan email yang terdaftar untuk mengirim kode otp ini",
				fontFamily = poppinsMedium,
				color = Color.Gray,
				fontSize = 13.sp,
				textAlign = TextAlign.Center
			)
			
			FormTextField(
				value = validationResetPassword.email.value,
				onValueChange = {
					validationResetPassword.email = validationResetPassword.email.copy(value = it)
				},
				label = "Email",
				placeholder = "masukan email terdaftar",
				error = validationResetPassword.email.showError,
				leadingIcon = Icons.Default.MarkEmailUnread,
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
			
			Button(
				onClick = {
					if (validationResetPassword.validateFormResetPassword()) {
//						authViewModel.requestOTP(
//							email = validationResetPassword.email.value,
//						)
						navController.navigate(
							route = VerificationRequestOTP(
								email = validationResetPassword.email.value
							)
						)
					}
					
				},
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp)
					.padding(horizontal = 16.dp, vertical = 8.dp)
					.shadow(elevation = 4.dp, shape = RoundedCornerShape(28.dp)),
				colors = ButtonDefaults.buttonColors(
					containerColor = greenColor, // Tu color verde definido
					contentColor = Color.White
				),
				shape = RoundedCornerShape(28.dp)
			) {
				Row (
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.Center
				) {
					Icon(
						imageVector = Icons.Default.VerifiedUser,
						contentDescription = null,
						modifier = Modifier.size(20.dp)
					)
					Spacer(modifier = Modifier.width(8.dp))
					Text(
						text = "Kirim OTP Sekarang",
						fontFamily = poppinsMedium,
						fontSize = 16.sp,
						fontWeight = FontWeight.SemiBold
					)
				}
			}
		}
		
		when(val state = requestOtp) {
			is StateManagement.Loading -> {
				Box(
					modifier = Modifier
						.align(Alignment.Center)
						.background(color = Color.White)
				) {
					LoadingLottieAnimation()
				}
			}
			
			is StateManagement.Error -> {
				Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
			}
			
			is StateManagement.RequestOtpSuccess -> {
				Toast.makeText(context, "KODE OTP BERHASIL DI KIRIM EMAIL", Toast.LENGTH_LONG).show()
				navController.navigate(
					route = VerificationRequestOTP(
						email = validationResetPassword.email.value
					)
				)
			}
			
			else -> authViewModel.clearAuthState()
		}
	}
	
	
	
	
	
}