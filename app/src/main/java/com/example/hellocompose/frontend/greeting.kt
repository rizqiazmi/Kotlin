package com.example.hellocompose.frontend

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hellocompose.PreferencesManager
import com.example.hellocompose.R
import com.example.hellocompose.data.LoginData
import com.example.hellocompose.respon.LoginRespon
import com.example.hellocompose.service.LoginService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(navController: NavController, context: Context = LocalContext.current) {

    val preferencesManager = remember { PreferencesManager(context = context) }
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var baseUrl = "http://10.0.2.2:1337/api/"
    var jwt by remember { mutableStateOf("") }
    val imageModifier = Modifier
        .size(140.dp)
        .offset(x = 135.dp, y = 25.dp)
    val imageModifier2 = Modifier
        .size(45.dp)
        .offset(x = 180.dp, y = 530.dp)
    val imageModifier3 = Modifier
        .size(45.dp)
        .offset(x = 115.dp, y = 530.dp)
    val imageModifier4 = Modifier
        .size(45.dp)
        .offset(x = 250.dp, y = 530.dp)

    jwt = preferencesManager.getData("jwt")
    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text(text = "Please sign to continue our app", fontWeight = FontWeight.Light, fontSize = 18.sp, modifier = Modifier.padding(bottom=10.dp))
            OutlinedTextField(value = username, onValueChange = { newText ->
                username = newText
            }, label = { Text("Username") })
            OutlinedTextField(value = password, onValueChange = { newText ->
                password = newText
            }, label = { Text("Password") })
            ElevatedButton(modifier = Modifier.width(300.dp).padding(top=20.dp), onClick = {
                //navController.navigate("pagetwo")
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(LoginService::class.java)
                val call = retrofit.getData(LoginData(username.text, password.text))
                call.enqueue(object : Callback<LoginRespon> {
                    override fun onResponse(
                        call: Call<LoginRespon>,
                        response: Response<LoginRespon>
                    ) {
                        print(response.code())
                        if (response.code() == 200) {
                            jwt = response.body()?.jwt!!
                            preferencesManager.saveData("jwt", jwt)
                            navController.navigate("Home")
                        } else if (response.code() == 400) {
                            print("error login")
                            Toast.makeText(
                                context,
                                "Username atau password salah",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }

                    override fun onFailure(call: Call<LoginRespon>, t: Throwable) {
                        print(t.message)
                    }

                })
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue, // Set your desired color here
                    contentColor = Color.White // Set text color
                )
                ) {
                Text(text = "Sign In")

            }
        }
    }
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "image description",
        contentScale = ContentScale.Crop,
        modifier = imageModifier
    )
    Image(
        painter = painterResource(id = R.drawable.instagram),
        contentDescription = "image description",
        contentScale = ContentScale.Crop,
        modifier = imageModifier2
    )
    Image(
        painter = painterResource(id = R.drawable.facebook),
        contentDescription = "image description",
        contentScale = ContentScale.Crop,
        modifier = imageModifier3
    )
    Image(
        painter = painterResource(id = R.drawable.twitter),
        contentDescription = "image description",
        contentScale = ContentScale.Crop,
        modifier = imageModifier4
    )
}

