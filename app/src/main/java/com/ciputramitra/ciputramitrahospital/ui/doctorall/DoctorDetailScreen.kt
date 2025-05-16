package com.ciputramitra.ciputramitrahospital.ui.doctorall

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowCircleLeft
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    Scaffold(
        topBar = {
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
        },

        bottomBar = {
            BottomAppBar(
                containerColor = whiteCustom
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp) ,
                    shape = RoundedCornerShape(8.dp) ,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.LightGray
                    ) ,
                    onClick = {
//                        isOpenCart = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth ,
                        contentDescription = null ,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(5.dp) ,
                        tint = Color.White
                    )

                    Text(
                        color = Color.White ,
                        text = "Reservasi" ,
                        fontFamily = poppinsMedium ,
                        fontSize = 12.sp ,
                        fontWeight = FontWeight.Bold ,
                        maxLines = 1 ,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp) ,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = greenColor ,
                    ) ,
                    shape = RoundedCornerShape(8.dp) ,
                    onClick = {
//                        isOpenCart = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Videocam ,
                        contentDescription = null ,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(5.dp) ,
                        tint = Color.White
                    )
                    Text(
                        text = "Konsultasi online" ,
                        fontFamily = poppinsMedium ,
                        fontSize = 12.sp ,
                        fontWeight = FontWeight.Bold ,
                        maxLines = 1 ,
                        overflow = TextOverflow.Ellipsis ,
                        color = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.Start
        ) {


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
                    
                    DetailProfileDoctor(
                        dataDoctor = state.doctorDetailResponse.data
                    )

                }

                else -> doctorAllViewModel.clearStateDetail()
            }

        }
    }


}

@Composable
fun DetailProfileDoctor(dataDoctor: Data) {
    val tabs = listOf("PROFIL", "JADWAL", "ULASAN")
    var selectedStatus by remember { mutableStateOf(value = tabs[0]) }
    val selectedColor = Color.Black
    val unSelectedColor = Color.Gray
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Tab Row
        TabRow(
            modifier = Modifier.padding(top = 10.dp),
            selectedTabIndex = tabs.indexOf(selectedStatus),
            indicator = { tabsPosition ->
                TabRowDefaults.PrimaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabsPosition[tabs.indexOf(selectedStatus)]),
                    color = smoothColor,
                    width = 100.dp,
                    height = 4.dp
                )
            },
            contentColor = selectedColor,
            containerColor = Color.White
        ) {
            tabs.forEachIndexed { _, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            fontFamily = poppinsMedium,
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp,
                            color = if (selectedStatus == title) selectedColor else unSelectedColor
                        )
                    },
                    selected = selectedStatus == title,
                    onClick = {
                        selectedStatus = title
                    }
                )
            }
        }
        
        // Konten berdasarkan tab yang dipilih dengan Box sebagai container
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f) // Penting! Ini membuat Box mengisi sisa ruang
        ) {
            when (selectedStatus) {
                "PROFIL" -> ProfileContent(dataDoctor)
                "JADWAL" -> JadwalContent(dataDoctor)
                "ULASAN" -> UlasanContent(dataDoctor)
            }
        }
    }
}

@Composable
fun UlasanContent(dataDoctor: Data) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Isi konten...
        // Item-item LazyColumn tetap seperti sebelumnya
        item {
            if (dataDoctor.ulasans.isEmpty())
                Text(
                    text = "Masih kosong"
                )
            else
                Text(
                    text = "Ada"
                )
        }
    }
}

@Composable
fun JadwalContent(dataDoctor: Data) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Isi konten...
        // Item-item LazyColumn tetap seperti sebelumnya
        item {
            Text(
                text = dataDoctor.users.role
            )
        }
    }
}

@Composable
fun ProfileContent(dataDoctor: Data) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Isi konten...
        // Item-item LazyColumn tetap seperti sebelumnya
        item {
            Text(
                text = dataDoctor.users.name
            )
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
                .size(90.dp)
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
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = black,
            )

            Text(
                text = "Poliklinik : ${dataDoctor.categoryPolyclinics.categoryPolyclinic}",
                fontFamily = poppinsMedium,
                fontSize = 12.sp,
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
            
            Row(
                modifier = Modifier.padding(
                    top = 2.dp
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "( Ulasan ${dataDoctor.ulasans.size} )",
                    fontFamily = poppinsLight,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                // Titik bulat status
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = if (dataDoctor.statusDokter != "AKTIF") Color.Gray else greenColor,
                            shape = CircleShape
                        )
                )
                
                // Text status
                Text(
                    modifier = Modifier,
                    text = if(dataDoctor.statusDokter != "AKTIF") "offline" else "online",
                    fontFamily = poppinsLight,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (dataDoctor.statusDokter != "AKTIF") Color.Gray else greenColor
                )
            }

        }
    }
}
