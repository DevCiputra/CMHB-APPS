package com.ciputramitra.ciputramitrahospital.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CircleNotifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ciputramitra.ciputramitrahospital.R
import com.ciputramitra.ciputramitrahospital.biometrics.BiometricScreen
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.component.PageIndicator
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.navgraph.Biometric
import com.ciputramitra.ciputramitrahospital.navgraph.ConsultationOnline
import com.ciputramitra.ciputramitrahospital.response.auth.User
import com.ciputramitra.ciputramitrahospital.response.category.Data
import com.ciputramitra.ciputramitrahospital.ui.theme.Pink
import com.ciputramitra.ciputramitrahospital.ui.theme.greenColor
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsLight
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium
import com.ciputramitra.ciputramitrahospital.ui.theme.whiteCustom
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController,
    fetchUser: User?,
    homeViewModel: HomeViewModel
) {

    val homeState by homeViewModel.homeState.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(
        pageCount = {
            3
        }
    )


    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        // TAMBAHKAN INI - fetchCategory() harus dipanggil saat HomeScreen di-compose pertama kali
        homeViewModel.fetchCategory()
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(
                nextPage, pageOffsetFraction = 0f
            )
        }
    }

    val image = remember {
        mutableStateListOf(
            "https://firebasestorage.googleapis.com/v0/b/kakaarab-4bfcf.appspot.com/o/1banner.jpg?alt=media&token=5e027330-2747-4166-99ca-4652bf398573",
            "https://firebasestorage.googleapis.com/v0/b/kakaarab-4bfcf.appspot.com/o/banner2.jpg?alt=media&token=5a351413-7a1b-4cbd-9f53-4ec864e77979",
            "https://firebasestorage.googleapis.com/v0/b/kakaarab-4bfcf.appspot.com/o/banner3.png?alt=media&token=ceba3cbf-1bbf-4e17-87b0-e86e1d13bb32"
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, top = 12.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(fetchUser?.avatar ?: "")
                            .error(R.drawable.hoodie_man_profile)
                            .fallback(R.drawable.hoodie_man_profile)
                            .build(),
                        contentDescription = "image_profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(shape = CircleShape)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(1.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Hi!",
                                fontSize = 19.sp,
                                fontFamily = poppinsMedium,
                                color = Color.Black
                            )

                            Text(
                                modifier = Modifier
                                    .padding(start = 2.dp),
                                text = fetchUser?.name?.uppercase() ?: "Hoodie man",
                                fontSize = 13.sp,
                                fontFamily = poppinsMedium,
                                color = Color.Gray,
                                overflow = TextOverflow.Ellipsis
                            )

                        }

                        BadgedBox(
                            badge = {
                                Badge(
                                    modifier = Modifier.offset(x = (- 7).dp , y = 1.dp),
                                    content = {
                                        Text(
                                            text = "0",
                                            fontSize = 10.sp ,
                                            fontFamily = poppinsMedium
                                        )
                                    }
                                )
                            }
                        ) {
                            IconButton(
                                onClick = {
//                            onClickCart()
                                }
                            ) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    imageVector = Icons.Default.CircleNotifications,
                                    contentDescription = null
                                )
                            }

                        }
                    }

                }

            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(start = 14.dp, end = 14.dp, top = 18.dp, bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clickable {
//                            onClick()
                            },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
                        border = BorderStroke(width = 1.dp, color = Color.LightGray)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = null,
                                tint = greenColor
                            )
                            Text(
                                text = "cari dokter anda...",
                                fontFamily = poppinsMedium,
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }
                    }


                }
            }

            HorizontalDivider(
                thickness = 2.dp,
                color = whiteCustom
            )
        }


        item {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                contentPadding = PaddingValues(horizontal = 16.dp),
                pageSpacing = 5.dp
            ) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = context)
                            .data(image[index])
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            PageIndicator(
                pageCount = image.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .background(color = Color.White)
            )
        }

        when(val state = homeState) {
            is StateManagement.Loading -> item { LoadingLottieAnimation() }

            is StateManagement.Error ->  item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Gagal memuat kategori")
                    Text(
                        text = "Coba lagi",
                        modifier = Modifier
                            .clickable { homeViewModel.fetchCategory() }
                            .padding(8.dp),
                        color = greenColor,
                        fontFamily = poppinsMedium
                    )
                }
            }

            is StateManagement.HomeSuccess -> {
                item {
                    CategoryScreen(
                        dataCategory = state.categoryResponse.data,
                        navController = navController
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(top = 6.dp),
                        color = whiteCustom,
                        thickness = 1.dp
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Rekomendasi Dokter",
                                fontFamily = poppinsMedium,
                                fontWeight = FontWeight.Medium,
                                fontSize = 19.sp,
                                color = Color.Black
                            )

                            Text(
                                text = "Konsultasi online dengan dokter kami",
                                fontFamily = poppinsLight,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                        }


                        Text(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(route = Biometric)
                                },
                            text = "Lihat semua",
                            fontFamily = poppinsMedium,
                            fontWeight = FontWeight.Medium,
                            color = greenColor
                        )
                    }
                }

            }

            else -> homeViewModel.clearHomeState()
        }


    }
}




@Composable
fun CategoryScreen(dataCategory: List<Data>, navController: NavController) {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(color = Color.White)
            .padding(end = 6.dp, start = 6.dp, top = 4.dp),
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dataCategory) { itemCategory ->
            Column(
                modifier = Modifier
                    .size(80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Menggunakan BadgedBox untuk menambahkan badge
                BadgedBox(
                    badge = {
                        when (itemCategory.id) {
                            1 -> {
                                Badge(
                                    containerColor = greenColor,
                                    contentColor = Color.White,
                                    modifier = Modifier.offset(x = 3.dp, y = 3.dp)
                                ) {
                                    Text(
                                        text = "new",
                                        fontSize = 6.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 2.dp)
                                    )
                                }
                            }
                            2, 3, 4 -> {
                                Badge(
                                    containerColor = Pink, // Orange color
                                    contentColor = Color.White,
                                    modifier = Modifier.offset(x = 3.dp, y = 3.dp)
                                ) {
                                    Text(
                                        text = "coming",
                                        fontSize = 6.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                ) {
                    // AsyncImage yang akan mendapat badge
                    AsyncImage(
                        modifier = Modifier
                            .size(33.dp)
                            .clip(shape = CircleShape)
                            .clickable {
                                when (itemCategory.id) {
                                    1 -> {
                                        navController.navigate(route = ConsultationOnline)
                                    }
                                }
                            },
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        model = ImageRequest.Builder(context = context)
                            .data(itemCategory.imageCategory)
                            .error(R.drawable.logo)
                            .build()
                    )
                }

                Text(
                    text = itemCategory.nameCategory,
                    fontFamily = poppinsMedium,
                    fontSize = 10.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}



