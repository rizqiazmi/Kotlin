package com.example.hellocompose.frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hellocompose.R

@Composable
fun Dashboard(navController: NavController) {
    val imageList = listOf(
        R.drawable.satu,
        R.drawable.dua,
        R.drawable.tiga,
        R.drawable.empat
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lonceng),
            contentDescription = "image description",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp, 50.dp) // Set the desired size here
                .clip(shape = RoundedCornerShape(8.dp))
                .align(Alignment.TopEnd)
        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "image description",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(130.dp, 80.dp) // Set the desired size here
                .clip(shape = RoundedCornerShape(8.dp))
                .align(Alignment.TopStart)
                .offset(y = (-11).dp)
        )

        Image(
            painter = painterResource(id = R.drawable.dashtext),
            contentDescription = "image description",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(320.dp, 250.dp) // Set the desired size here
                .clip(shape = RoundedCornerShape(8.dp))
        )

    }
//    Spacer(modifier = Modifier.padding(vertical = 50.dp))
    LazyRow(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(top = 200.dp)) {
        items(imageList) { image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(300.dp)
                    .height(330.dp)
                    .padding(all = 12.dp),
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = "image description",
            contentScale = ContentScale.Crop, // Use Crop to maintain aspect ratio and cover the entire space
            modifier = Modifier
                .fillMaxWidth() // Cover the entire width
                .height(100.dp) // Set the desired height
                .align(Alignment.BottomCenter)
                .clickable { navController.navigate("pagetwo") }
        )

        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .offset(x = 160.dp, y = 570.dp)
                .clickable { navController.navigate("Cari") }
        )

        Image(
            painter = painterResource(id = R.drawable.kalender),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .offset(x = 85.dp, y = 575.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.rumah),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .offset(x = 5.dp, y = 575.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.pesan),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .offset(x = 255.dp, y = 575.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.profil),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .offset(x = 335.dp, y = 575.dp)
                .clickable { navController.navigate("Profile") }
        )
    }
}

