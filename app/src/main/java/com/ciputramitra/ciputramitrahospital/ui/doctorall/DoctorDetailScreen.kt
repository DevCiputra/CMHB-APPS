package com.ciputramitra.ciputramitrahospital.ui.doctorall

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.ArrowCircleLeft
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Medication
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.RateReview
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material.icons.rounded.Videocam
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ciputramitra.ciputramitrahospital.R
import com.ciputramitra.ciputramitrahospital.component.EmptyStateView
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.component.formatDate
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.response.doctordetail.Data
import com.ciputramitra.ciputramitrahospital.response.doctordetail.Jadwal
import com.ciputramitra.ciputramitrahospital.response.doctordetail.Medic
import com.ciputramitra.ciputramitrahospital.response.doctordetail.Pendidikan
import com.ciputramitra.ciputramitrahospital.response.doctordetail.Pengalaman
import com.ciputramitra.ciputramitrahospital.response.doctordetail.Ulasans
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
                EmptyStateView(
                    message = "Ulasan belum tersedia. Bagikan pengalaman Anda setelah konsultasi"
                )
            else
                ReviewsPatient(
                    dataDoctor = dataDoctor.ulasans
                )
        }
        
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ReviewsPatient(dataDoctor: List<Ulasans>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Header with title and overall rating
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ulasan Pasien",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1F2937)
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFEAB308),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = if (dataDoctor.isNotEmpty()) {
                            val averageRating = dataDoctor.map { it.rating }.average()
                            String.format("%.1f", averageRating)
                        } else "0.0",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1F2937),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    Text(
                        text = "(${dataDoctor.size})",
                        fontSize = 12.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            
            HorizontalDivider(color = whiteCustom)
            
            if (dataDoctor.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Masih Kosong",
                        color = Color(0xFF6B7280),
                        fontSize = 14.sp
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                
                // List of reviews
                dataDoctor.forEach { itemReviews ->
                    ReviewItem(itemReviews)
                    
                    if (itemReviews != dataDoctor.last()) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = Color(0xFFE5E7EB)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewItem(review: Ulasans) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFE5E7EB), CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = review.namePatient.first().uppercase(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF6B7280)
            )
        }
        
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f)
        ) {
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = review.namePatient,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1F2937)
                )
                
                Text(
                    text = formatDate(review.createdAt),
                    fontSize = 12.sp,
                    color = Color(0xFF6B7280)
                )
            }
            
           
            Row(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = if (index < review.rating) Color(0xFFEAB308) else Color(0xFFE5E7EB),
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 2.dp)
                    )
                }
            }
            
           
            Text(
                text = review.reviewsPatient,
                fontSize = 14.sp,
                color = Color(0xFF4B5563),
                lineHeight = 20.sp
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
        
        item {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.CalendarMonth,
                    contentDescription = "icon_profile",
                    tint = greenColor,
                )
                
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = "Jadwal Praktek",
                    fontFamily = poppinsMedium,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        
        item {
            PracticeSchedule(
                dataDoctor = dataDoctor.jadwals
            )
        }
    }
}

@Composable
fun PracticeSchedule(dataDoctor: List<Jadwal>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (dataDoctor.isEmpty()) {
                Text(
                    text = "Belum ada jadwal",
                    fontFamily = poppinsMedium,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            } else {
                
                // Tampilkan jadwal
                dataDoctor.forEach { itemSchedule ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Tampilkan hari (3 huruf)
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Ambil 3 huruf pertama dari nama hari
                            val shortDay = if (itemSchedule.hari.length >= 3) {
                                itemSchedule.hari.substring(0, 3).uppercase()
                            } else {
                                itemSchedule.hari.uppercase() // Gunakan lengkap jika kurang dari 3 huruf
                            }
                            
                            Text(
                                modifier = Modifier
                                    .background(
                                        color = smoothColor,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                text = shortDay,
                                fontSize = 12.sp,
                                fontFamily = poppinsMedium,
                                fontWeight = FontWeight.Bold,
                                color = whiteCustom
                            )
                            
                            Column(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Text(
                                    modifier = Modifier.padding(start = 16.dp),
                                    text = itemSchedule.hari,
                                    fontSize = 14.sp,
                                    fontFamily = poppinsMedium,
                                    color = black,
                                    fontWeight = FontWeight.Medium
                                )
                                
                                Row(
                                    modifier = Modifier.padding(start = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(12.dp),
                                        imageVector = Icons.Rounded.AccessTime,
                                        contentDescription = null,
                                        tint = Color.Gray,
                                    )
                                    
                                    // Tampilkan jam praktek
                                    Text(
                                        text = itemSchedule.jadwalJam,
                                        fontFamily = poppinsLight,
                                        fontSize = 14.sp,
                                        color = Color.Gray,
                                    )
                                }
                                
                            }
                            
                        }
                        
                        // Jika ada informasi tambahan seperti status tersedia atau tidak
                        // Anda bisa menambahkannya di sini
                        Text(
                            text = "Tersedia",
                            fontFamily = poppinsLight,
                            fontSize = 12.sp,
                            color = greenColor
                        )
                    }
                    
                    // Tambahkan divider kecuali untuk item terakhir
                    if (itemSchedule != dataDoctor.last()) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = Color.LightGray.copy(alpha = 0.5f),
                            thickness = 0.5.dp
                        )
                    }
                }
            }
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
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "icon_profile",
                    tint = greenColor,
                )
                
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = "BIOGRAFI",
                    fontFamily = poppinsMedium,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        
        item {
            ExpandableBiography(
                text = dataDoctor.biografi
            )
        }
        
        item {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.School,
                    contentDescription = "icon_education",
                    tint = greenColor,
                )
                
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = "Pendidikan",
                    fontFamily = poppinsMedium,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        
        item {
            ExpandableEducation(
                dataEducation = dataDoctor.pendidikans
            )
        }
        
        item {
            Row(
                modifier = Modifier
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = "icon_experience",
                    tint = greenColor,
                )
                
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = "Pengalaman Praktek",
                    fontFamily = poppinsMedium,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        
        item {
            ExpandableAction(
                dataDoctor = dataDoctor.medics
            )
        }
        
        item {
            Row(
                modifier = Modifier
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Medication,
                    contentDescription = "icon_medic",
                    tint = greenColor,
                )
                
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = "Tindakan Medis",
                    fontFamily = poppinsMedium,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        
        item {
            ExpandableExperience(
                dataDoctor = dataDoctor.pengalamans
            )
        }
    }
}

@Composable
fun ExpandableAction(dataDoctor: List<Medic>) {
    // State untuk melacak apakah daftar tindakan medis sedang diperluas atau tidak
    var isExpanded by remember { mutableStateOf(false) }
    
    // Tampilkan maksimal 2 item jika tidak diperluas, atau semua item jika diperluas
    val dataToShow = if (isExpanded) dataDoctor else dataDoctor.take(2)
    
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (dataDoctor.isEmpty()) {
            Text(
                text = "-",
                fontFamily = poppinsMedium,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        } else {
            // Daftar tindakan medis
            dataToShow.forEach { itemMedic ->
                Text(
                    text = "- ${itemMedic.namaTindakanMedis}",
                    fontFamily = poppinsMedium,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
            
            // Tombol expand/collapse hanya jika ada lebih dari 2 item tindakan medis
            if (dataDoctor.size > 2) {
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isExpanded) "Lihat Lebih Sedikit" else "Lihat ${dataDoctor.size - 2} Lainnya",
                        fontFamily = poppinsMedium,
                        fontSize = 12.sp,
                        color = Color(0xFF4E97FD),
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        tint = Color(0xFF4E97FD),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ExpandableExperience(dataDoctor: List<Pengalaman>) {
    // State untuk melacak apakah daftar pengalaman sedang diperluas atau tidak
    var isExpanded by remember { mutableStateOf(false) }
    
    // Tampilkan maksimal 3 item jika tidak diperluas, atau semua item jika diperluas
    val dataToShow = if (isExpanded) dataDoctor else dataDoctor.take(1)
    
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (dataDoctor.isEmpty()) {
            Text(
                text = "-",
                fontFamily = poppinsMedium,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        } else {
            // Daftar pengalaman
            dataToShow.forEach { itemExperience ->
                Text(
                    text = "- ${itemExperience.namaPengalamanPraktek}",
                    fontFamily = poppinsMedium,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
            
            // Tombol expand/collapse hanya jika ada lebih dari 3 item pengalaman
            if (dataDoctor.size > 1) {
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isExpanded) "Lihat Lebih Sedikit" else "Lihat ${dataDoctor.size - 1} Lainnya",
                        fontFamily = poppinsMedium,
                        fontSize = 12.sp,
                        color = Color(0xFF4E97FD),
                        modifier = Modifier
                            .padding(end = 4.dp)
                    )
                    
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        tint = Color(0xFF4E97FD),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ExpandableEducation(dataEducation: List<Pendidikan>) {
    // State untuk melacak apakah daftar pendidikan sedang diperluas atau tidak
    var isExpanded by remember { mutableStateOf(false) }
    
    // Tampilkan maksimal 2 item jika tidak diperluas, atau semua item jika diperluas
    val dataToShow = if (isExpanded) dataEducation else dataEducation.take(2)
    
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (dataEducation.isEmpty()) {
            Text(
                text = "-",
                fontFamily = poppinsMedium,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        } else {
            // Daftar pendidikan
            dataToShow.forEach { itemEducation ->
                Text(
                    text = "- ${itemEducation.namaRiwayatPendidikan}",
                    fontFamily = poppinsMedium,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
            
            // Tombol expand/collapse hanya jika ada lebih dari 2 item pendidikan
            if (dataEducation.size > 2) {
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isExpanded) "Lihat Lebih Sedikit" else "Lihat ${dataEducation.size - 2} Lainnya",
                        fontFamily = poppinsMedium,
                        fontSize = 12.sp,
                        color = Color(0xFF4E97FD),
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        tint = Color(0xFF4E97FD),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun ExpandableBiography(text: String) {
    // State untuk melacak apakah teks sedang diperluas atau tidak
    var isExpanded by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontFamily = poppinsLight,
            fontSize = 12.sp,
            maxLines = if (isExpanded) Int.MAX_VALUE else 4, // Tidak terbatas saat diperluas
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        
        // Row untuk tombol expand/collapse dengan arrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isExpanded) "Lihat Lebih Sedikit" else "Lihat Selengkapnya",
                fontFamily = poppinsMedium,
                fontSize = 12.sp,
                color = Color(0xFF4E97FD),
                modifier = Modifier.padding(end = 4.dp)
            )
            
            Icon(
                imageVector = if (isExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                tint = Color(0xFF4E97FD),
                modifier = Modifier.size(16.dp)
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
                .fillMaxWidth()
                .padding(top = 3.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {

            Text(
                text = "${dataDoctor.users.role}. ${dataDoctor.users.name}",
                fontFamily = poppinsMedium,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
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
