package com.ciputramitra.ciputramitrahospital.ui.doctorall

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowCircleLeft
import androidx.compose.material.icons.rounded.ArrowCircleRight
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ciputramitra.ciputramitrahospital.R
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.response.doctordetail.Data
import com.ciputramitra.ciputramitrahospital.ui.theme.black
import com.ciputramitra.ciputramitrahospital.ui.theme.greenColor
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsBold
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsLight
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium
import com.ciputramitra.ciputramitrahospital.ui.theme.smoothColor
import com.ciputramitra.ciputramitrahospital.ui.theme.whiteCustom


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetailScreen(
    doctorAllViewModel: DoctorAllViewModel,
    navController: NavHostController,
    doctorID: Int
) {



    val doctorDetailState by doctorAllViewModel.doctorDetailState.collectAsStateWithLifecycle()


    LaunchedEffect(key1 = doctorID) {
        doctorAllViewModel.fetchDoctorDetail(id = doctorID)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = greenColor,
            ),
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = "Profil Dokter",
                        fontFamily = poppinsBold,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        color = whiteCustom
                    )

                    Icon(
                        modifier = Modifier.padding(end = 12.dp),
                        imageVector = Icons.Rounded.Share,
                        contentDescription = null,
                        tint = whiteCustom,
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
                        modifier = Modifier.padding(start = 16.dp),
                        imageVector = Icons.Rounded.ArrowCircleLeft,
                        contentDescription = null,
                        tint = whiteCustom
                    )
                }
            }
        )

        when (val state = doctorDetailState) {
            is StateManagement.Loading -> {
                LoadingLottieAnimation()
            }

            is StateManagement.Error -> Toast.makeText(
                LocalContext.current ,
                state.message ,
                Toast.LENGTH_SHORT
            ).show()

            is StateManagement.DoctorDetailSuccess -> {
                HeaderProfileDoctor(
                    dataDoctor = state.doctorDetailResponse.data
                )

                HorizontalDivider(
                    modifier = Modifier.padding(top = 10.dp, bottom = 4.dp),
                    thickness = 3.dp,
                    color = whiteCustom
                )

            }

            else -> doctorAllViewModel.clearStateDetail()
        }

    }
}


@Composable
fun HeaderProfileDoctor(dataDoctor: Data) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp , end =  16.dp, top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(dataDoctor.users.avatar)
                .error(R.drawable.logo)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {

            Text(
                text = "${dataDoctor.users.role}. ${dataDoctor.users.name}",
                fontFamily = poppinsMedium,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = black,
            )

            Text(
                text = "Poliklinik : ${dataDoctor.categoryPolyclinics.categoryPolyclinic}",
                fontFamily = poppinsMedium,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier
                    .background(color = smoothColor, shape = CircleShape)
                    .padding(4.dp),
                text = dataDoctor.spesialisName,
                fontFamily = poppinsLight,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = whiteCustom
            )


        }
    }
}
