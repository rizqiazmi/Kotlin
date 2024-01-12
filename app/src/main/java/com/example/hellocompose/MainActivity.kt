package com.example.hellocompose

import Welcome
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hellocompose.frontend.CreateUserPage
import com.example.hellocompose.frontend.Dashboard
import com.example.hellocompose.frontend.EditUserPage
import com.example.hellocompose.frontend.Greeting
import com.example.hellocompose.frontend.Homepage
import com.example.hellocompose.frontend.Popularplaces
import com.example.hellocompose.frontend.UserProfile
import kotlinx.coroutines.launch

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
                            label = { Text(text = "Dashboard") },
                            selected = false,
                            onClick = {
                                navController.navigate("Home")
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )

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
                        composable(route = "Home") {
                            Dashboard(navController)
                        }
                        composable(route = "Cari") {
                            Popularplaces(navController)
                        }
                        composable(route = "Profile") {
                            UserProfile(navController)
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
        }
    }

