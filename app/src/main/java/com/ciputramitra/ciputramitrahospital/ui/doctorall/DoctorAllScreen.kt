package com.ciputramitra.ciputramitrahospital.ui.doctorall

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ciputramitra.ciputramitrahospital.R
import com.ciputramitra.ciputramitrahospital.component.InformationBusy
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.response.doctorall.DoctorAllItems
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium
import com.ciputramitra.ciputramitrahospital.ui.theme.whiteCustom


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorAllScreen(
    doctorAllViewModel: DoctorAllViewModel,
    navController: NavController,
    categoryPolyclinicID: Int,
    nameCategoryPolyclinic: String
) {

    val doctor = doctorAllViewModel.doctors.collectAsLazyPagingItems()
    var nameSpecialist by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        doctorAllViewModel.fetchDoctorAll(
            specialName = "",
            categoryPolyclinicID = categoryPolyclinicID,
            cheap = "",
            expensive = "",
            consultationStatus = "",
            reservationStatus = "",
            statusDoctor = ""
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        stickyHeader {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        text = nameCategoryPolyclinic,
                        fontFamily = poppinsMedium,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        },

                        ) {
                        Icon(
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                            imageVector = Icons.Default.ArrowCircleLeft,
                            contentDescription = null,
                        )
                    }

                }
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = whiteCustom
            )

            SearchSpecialis(
                SpecialistName = nameSpecialist,
                onNameSpecialistChange = { nameSpecialist = it },
                onSearchClicked = {
                    doctorAllViewModel.fetchDoctorAll(
                        specialName = nameSpecialist
                    )
                }
            )

        }

        items(
            count = doctor.itemCount,
            key = doctor.itemKey { it.id }
        ) {
            val doctors = doctor[it]
            doctors?.let { doctorsItems ->
                DoctorAllItem(
                    doctorsItems = doctorsItems,
                    navController = navController
                )
            }
        }

        doctor.apply {
            when {
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> item {
                    LoadingLottieAnimation()
                }

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> item {
                    InformationBusy(message = "Mohon maaf server sedang sibuk", onRetryClick = { retry() })
                }
            }
        }
    }
}

@Composable
fun SearchSpecialis(
    SpecialistName: String,
    onNameSpecialistChange: (String) -> Unit,
    onSearchClicked: () -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            ),
        value = SpecialistName,
        onValueChange = onNameSpecialistChange,
        placeholder = {
            Text(
                text = "silahkan cari spesialis",
            )
        },
        textStyle = TextStyle(
            fontFamily = poppinsMedium,
            fontSize = 12.sp,
            color = Color.Gray
        ) ,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.LightGray
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchClicked()})
    )
}

@Composable
fun DoctorAllItem(
    doctorsItems: DoctorAllItems,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(doctorsItems.users.avatar)
                .error(R.drawable.logo)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = doctorsItems.users.name
            )

            Text(
                text = doctorsItems.spesialisName
            )

            Text(
                text = doctorsItems.categoryPolyclinics.categoryPolyclinic
            )
        }
    }
}

