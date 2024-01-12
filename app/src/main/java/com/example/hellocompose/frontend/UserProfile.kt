package com.example.hellocompose.frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hellocompose.R

@Composable
fun UserProfile(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.TopStart)
                .clickable { navController.navigate("Home") }
        )

        Image(
            painter = painterResource(id = R.drawable.userprofile),
            contentDescription = "image description",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.Center)
                .offset(y = (-90).dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Text(
//                text = "Profil",
//                textAlign = TextAlign.Center,
//                fontSize = 20.sp, // Set the desired text size
//                fontWeight = FontWeight.Light,
//                color = Color.Gray,
//                modifier = Modifier.offset(y = (-90).dp)
//                    .align(Alignment.TopCenter)
//            )
        }
    }
}