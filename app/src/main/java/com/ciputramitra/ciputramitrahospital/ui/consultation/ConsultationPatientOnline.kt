package com.ciputramitra.ciputramitrahospital.ui.consultation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ciputramitra.ciputramitrahospital.R
import com.ciputramitra.ciputramitrahospital.component.LoadingLottieAnimation
import com.ciputramitra.ciputramitrahospital.domain.state.StateManagement
import com.ciputramitra.ciputramitrahospital.response.categorypoly.Data
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsLight
import com.ciputramitra.ciputramitrahospital.ui.theme.poppinsMedium
import com.ciputramitra.ciputramitrahospital.ui.theme.whiteCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultationPatientOnline(
    consultationViewModel: ConsultationViewModel,
    navController: NavController
) {

    val categoryPolyclinicState by consultationViewModel.categoryPolyclinic.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        consultationViewModel.fetchCategoryPolyclinic()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        stickyHeader {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        text = "Spesialisasi",
                        fontFamily = poppinsMedium,
                        fontWeight = FontWeight.Medium
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
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 14.dp, start = 14.dp, top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Spesialisasi",
                    fontFamily = poppinsMedium,
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
                    letterSpacing = TextUnit.Unspecified
                )

                Text(
                    fontFamily = poppinsLight,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    text = "Konsultasi dengan Dokter Spesialis"
                )
            }
        }

        when(val state = categoryPolyclinicState) {
            is StateManagement.Loading -> item { LoadingLottieAnimation() }
            is StateManagement.Error -> Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            is StateManagement.CategoryPolyclinicSuccess -> {
                item {
                    CategoryPolyclinic(
                        dataCategoryPolyclinic = state.categoryPolyclinicResponse.data,
                    )
                }
            }

            else -> consultationViewModel.clearCategoryPolyclinicState()
        }
    }

}

@Composable
fun CategoryPolyclinic(dataCategoryPolyclinic: List<Data>) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 300.dp, max = 800.dp)
    ) {

        items(dataCategoryPolyclinic) { categoryPolyclinic ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data(categoryPolyclinic.imageCategoryPoly)
                        .error(R.drawable.logo)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 30.dp, height = 30.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = categoryPolyclinic.categoryPolyclinic,
                    fontFamily = poppinsLight,
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
