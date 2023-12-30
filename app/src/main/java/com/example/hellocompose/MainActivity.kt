package com.example.hellocompose

import Welcome
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hellocompose.data.LoginData
import com.example.hellocompose.frontend.CreateUserPage
import com.example.hellocompose.frontend.EditUserPage
import com.example.hellocompose.frontend.Greeting
import com.example.hellocompose.frontend.Homepage
import com.example.hellocompose.respon.LoginRespon
import com.example.hellocompose.service.LoginService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val preferencesManager = remember { PreferencesManager(context = LocalContext.current) }
            val sharedPreferences: SharedPreferences =
                LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
            val navController = rememberNavController()

            var startDestination: String
            var jwt = sharedPreferences.getString("jwt", "")
            if (jwt.equals("")) {
                startDestination = "Splash"
            } else {
                startDestination = "Splash"
            }
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        Text("Galon", modifier = Modifier.padding(16.dp))
                        Divider()
                        NavigationDrawerItem(
                            label = { Text(text = "Add User") },
                            selected = false,
                            onClick = {
                                navController.navigate("createuserpage")
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )

                        NavigationDrawerItem(
                            label = { Text(text = "Logout") },
                            selected = false,
                            onClick = {
                                navController.navigate("greeting")
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                        // ...other drawer items
                    }
                }
            ) {
                    // Screen content
                    NavHost(
                        navController,
                        startDestination = startDestination,
                    ) {
                        composable(route = "Splash") {
                            Welcome(navController)
                        }
                        composable(route = "greeting") {
                            Greeting(navController)
                        }
                        composable(route = "pagetwo") {
                            Homepage(navController)
                        }
                        composable(route = "createuserpage") {
                            CreateUserPage(navController)
                        }
                        composable(
                            route = "edituserpage/{userid}/{username}",
                        ) { backStackEntry ->

                            EditUserPage(
                                navController,
                                backStackEntry.arguments?.getString("userid"),
                                backStackEntry.arguments?.getString("username")
                            )
                        }
                    }
                }
            }

//            NavHost(navController, startDestination = startDestination) {
//                composable(route = "greeting") {
//                    Greeting(navController)
//                }
//                composable(route = "pagetwo") {
//                    Homepage(navController)
//                }
//                composable(route = "createuserpage") {
//                    CreateUserPage(navController)
//                }
//                composable(route = "edituserpage/{userid}/{username}",
//                    ) {backStackEntry ->
//
//                    EditUserPage(navController, backStackEntry.arguments?.getString("userid"), backStackEntry.arguments?.getString("username"))
//                }
//            }
        }
    }

