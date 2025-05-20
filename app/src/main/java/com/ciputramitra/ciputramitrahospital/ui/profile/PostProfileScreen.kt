package com.ciputramitra.ciputramitrahospital.ui.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bloodtype
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.rounded.ArrowCircleLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ciputramitra.ciputramitrahospital.component.FormTextField
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.response.auth.User
import com.ciputramitra.ciputramitrahospital.ui.sign.ValidationsAuth
import com.ciputramitra.ciputramitrahospital.ui.theme.black
import com.ciputramitra.ciputramitrahospital.ui.theme.greenColor
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsBold
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium
import com.ciputramitra.ciputramitrahospital.ui.theme.whiteCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostProfileScreen(
	navController: NavController,
	profilePatientViewModel: ProfilePatientViewModel,
	fetchUser: User?
) {
	
	// Animation
	val animatedProgress = remember { Animatable(0f) }
	LaunchedEffect(Unit) {
		animatedProgress.animateTo(
			targetValue = 1f,
			animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
		)
	}
	
	
	
	
	val validationProfile: ValidationProfile = viewModel()
	val profileData by profilePatientViewModel.profilePatientState.collectAsStateWithLifecycle()
	val context = LocalContext.current
	
	// Gender options
	val genderOptions = listOf("Pria", "Perempuan")
	
	// Golongan darah options
	val bloodList = listOf("A", "B", "AB", "O", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
	// State untuk melacak golongan darah yang dipilih
	var selectedBloodType by remember { mutableStateOf<String?>(null) }
	
	
	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = greenColor,
					titleContentColor = whiteCustom,
					navigationIconContentColor = whiteCustom
				),
				title = {
					Column {
						Text(
							text = "Buat Profil Pasien",
							fontFamily = poppinsMedium,
							fontSize = 17.sp,
							fontWeight = FontWeight.SemiBold,
							color = whiteCustom
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
							imageVector = Icons.Rounded.ArrowCircleLeft,
							contentDescription = null,
							tint = whiteCustom
						)
					}
				}
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
					.padding(vertical = 16.dp)
					.verticalScroll(state = rememberScrollState())
			) {
				
				LazyRow(
					modifier = Modifier
						.padding(horizontal = 14.dp)
						.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					items(bloodList) { bloodItem ->
						
						// Cek apakah item ini dipilih
						val isSelected = bloodItem == selectedBloodType
						
						Box(
							modifier = Modifier
								.background(
									color = if (isSelected) MaterialTheme.colorScheme.primary
									else MaterialTheme.colorScheme.surface,
									shape = RoundedCornerShape(8.dp)
								)
								.border(
									width = 1.dp,
									color = if (isSelected) MaterialTheme.colorScheme.primary
									else MaterialTheme.colorScheme.outline,
									shape = RoundedCornerShape(8.dp)
								)
								.padding(horizontal = 16.dp, vertical = 8.dp)
								.clickable {
									// Update item yang dipilih dan tampilkan toast
									selectedBloodType = bloodItem
									Toast.makeText(context, "Selected: $bloodItem", Toast.LENGTH_SHORT).show()
								},
							contentAlignment = Alignment.Center
						) {
							Row(
								verticalAlignment = Alignment.CenterVertically,
								horizontalArrangement = Arrangement.spacedBy(4.dp)
							) {
								Text(
									text = bloodItem,
									color = if (isSelected) MaterialTheme.colorScheme.onPrimary
									else MaterialTheme.colorScheme.onSurface
								)
								
								// Tampilkan checkmark jika dipilih
								if (isSelected) {
									Icon(
										imageVector = Icons.Default.Check,
										contentDescription = "Selected",
										tint = MaterialTheme.colorScheme.onPrimary,
										modifier = Modifier.size(16.dp)
									)
								}
							}
						}
					}
				}
				
				
				
				FormTextField(
					value = selectedBloodType ?: "",
					onValueChange = {},
					label = "Golongan darah ( wajib )",
					error = validationProfile.gender.showError,
					leadingIcon = Icons.Default.Bloodtype,
					keyboardType = KeyboardType.Text,
					singleLine = true,
					suffix = {
						Text(
							text = ".com",
							fontSize = 12.sp,
							color = Color.LightGray
						)
					},
					placeholder = {
						Text(
							text = "silahkan pilih di atas golongan darah anda",
							fontFamily = poppinsMedium,
							fontSize = 14.sp,
							color = black
						)
					},
					enable = false
				)
			}
		}
	}
}