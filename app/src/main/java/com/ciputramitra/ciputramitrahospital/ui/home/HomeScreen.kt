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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CircleNotifications
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ciputramitra.ciputramitrahospital.R
import com.ciputramitra.ciputramitrahospital.component.PageIndicator
import com.ciputramitra.ciputramitrahospital.response.auth.User
import com.ciputramitra.ciputramitrahospital.ui.theme.greenColor
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController,
    fetchUser: User?
) {


    val pagerState = rememberPagerState(
        pageCount = {
            3
        }
    )


    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
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
            "https://firebasestorage.googleapis.com/v0/b/shoppy-22293.appspot.com/o/mcu3.jpg?alt=media&token=8a2d526e-dfa5-4166-81f3-84b3083cd0f8",
            "https://firebasestorage.googleapis.com/v0/b/shoppy-22293.appspot.com/o/mcu4.jpg?alt=media&token=24be3c6a-2589-4d9e-a9b0-dbb5c192e693",
            "https://firebasestorage.googleapis.com/v0/b/shoppy-22293.appspot.com/o/mcu5.jpg?alt=media&token=0e7bf46c-d8cf-49a1-98c5-cef08062ec52"
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(fetchUser?.profilePicture ?: "")
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
                            text = "Good morning",
                            fontSize = 19.sp,
                            fontFamily = poppinsMedium,
                            color = Color.Black
                        )

                        Text(
                            modifier = Modifier
                                .padding(start = 2.dp),
                            text = fetchUser?.username ?: "Hoodie man",
                            fontSize = 14.sp,
                            fontFamily = poppinsMedium,
                            color = Color.Gray
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
                                modifier = Modifier.size(27.dp),
                                imageVector = Icons.Default.CircleNotifications,
                                contentDescription = null
                            )
                        }

                    }
                }

            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(start = 14.dp, end = 14.dp, top = 4.dp)
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
                        shape = RoundedCornerShape(16.dp),
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
                                imageVector = Icons.Default.PersonSearch,
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
                        .height(200.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = context)
                            .data(image[index])
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .height(200.dp),
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
    }
}



//@Preview
//@Composable
//fun Header() {
//    HeaderBar()
//}


