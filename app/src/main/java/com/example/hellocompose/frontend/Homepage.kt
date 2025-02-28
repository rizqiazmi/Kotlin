package com.example.hellocompose.frontend

import android.content.ClipData.Item
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Menu
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hellocompose.data.LoginData
import com.example.hellocompose.data.RegisterData
import com.example.hellocompose.respon.LoginRespon
import com.example.hellocompose.respon.UserRespon
import com.example.hellocompose.service.LoginService
import com.example.hellocompose.service.RegisterService
import com.example.hellocompose.service.UserService
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(navController: NavController, context: Context = LocalContext.current){
    //var listUser: List<UserRespon> = remember
    val chartEntryModel = entryModelOf(4f, 12f, 8f, 16f)
    val dataPoint = arrayListOf<FloatEntry>()
    val modelProducer = ChartEntryModelProducer()
    val dataSetForModel = remember { mutableStateListOf(listOf<FloatEntry>()) }
    var username by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
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
    ){}
    dataPoint.add(FloatEntry(3f, 5f))
    dataPoint.add(FloatEntry(2f, 7f))
    dataSetForModel.add(dataPoint)
    modelProducer.setEntries(dataSetForModel)

    val listUser = remember { mutableStateListOf<UserRespon>()}
    //var listUser: List<UserRespon> by remember { mutableStateOf(List<UserRespon>()) }
    var baseUrl = "http://10.0.2.2:1337/api/"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserService::class.java)

    fun loadData(search: String?){
        val call = retrofit.getData(search)
        call.enqueue(object : Callback<List<UserRespon>> {
            override fun onResponse(
                call: Call<List<UserRespon>>,
                response: Response<List<UserRespon>>
            ) {
                if (response.code() == 200) {
                    //kosongkan list User terlebih dahulu
                    listUser.clear()
                    response.body()?.forEach{ userRespon ->
                        listUser.add(userRespon)
                    }
                } else if (response.code() == 400) {
                    print("error login")
                    var toast = Toast.makeText(
                        context,
                        "Username atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<UserRespon>>, t: Throwable) {
                print(t.message)
            }

        })
    }

    loadData(null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Galon") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Outlined.Menu, contentDescription = "")
                    }
                }
            )

        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show drawer") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )
        }

        ) { innerPadding ->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        ) {
            Chart(
                chart = lineChart(),
                // model = chartEntryModel,
                chartModelProducer = modelProducer,
                startAxis = rememberStartAxis(),
                bottomAxis = rememberBottomAxis(),
            )
            LazyColumn{
                item { Row {
                    OutlinedTextField(value = username, onValueChange = { newText ->
                        username = newText
                        loadData(username.text)
                    })
                }}
                listUser.forEach { user ->
                    item {
                        Row (modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = user.username)
                            Row {
                                ElevatedButton(onClick = {
                                    val retrofit = Retrofit.Builder()
                                        .baseUrl(baseUrl)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()
                                        .create(UserService::class.java)
                                    val call = retrofit.delete(user.id)
                                    call.enqueue(object : Callback<UserRespon> {
                                        override fun onResponse(
                                            call: Call<UserRespon>,
                                            response: Response<UserRespon>
                                        ) {
                                            print(response.code())
                                            if (response.code() == 200) {
                                                listUser.remove(user)
                                            } else if (response.code() == 400) {
                                                print("error login")
                                                var toast = Toast.makeText(
                                                    context,
                                                    "Username atau password salah",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                            }
                                        }

                                        override fun onFailure(
                                            call: Call<UserRespon>,
                                            t: Throwable
                                        ) {
                                            print(t.message)
                                        }

                                    })
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.width(20.dp))
                                    Box(modifier = Modifier.padding(horizontal = 2.dp))
                                    Text("Delete")
                                }
                                Box(modifier = Modifier.padding(horizontal = 5.dp))
                                ElevatedButton(onClick = {
                                    navController.navigate("edituserpage/" + user.id + "/" + user.username)
                                }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Edit", modifier = Modifier.width(20.dp))
                                    Box(modifier = Modifier.padding(horizontal = 2.dp))
                                    Text("Edit")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}