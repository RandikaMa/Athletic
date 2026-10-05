package com.example.atc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import kotlinx.coroutines.launch

class HomeComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreen()
        }
    }
}

data class HomeMenuItem(
    val title: String,
    val imageRes: Int,
    val destination: Class<*>? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val context = androidx.compose.ui.platform.LocalContext.current

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val menuItems = listOf(

        HomeMenuItem(
            title = "Doctors",
            imageRes = R.drawable.doctor,
            destination = ShowDoctors::class.java
        ),

        HomeMenuItem(
            title = "Therapist",
            imageRes = R.drawable.therapy
        ),

        HomeMenuItem(
            title = "Events",
            imageRes = R.drawable.event,
            destination = EventApp::class.java
        ),

        HomeMenuItem(
            title = "Performance",
            imageRes = R.drawable.perfo
        ),

        HomeMenuItem(
            title = "Athletes",
            imageRes = R.drawable.athlete,
            destination = ShowAthletes::class.java
        ),

        HomeMenuItem(
            title = "Coaches",
            imageRes = R.drawable.coach,
            destination = ShowCoachers::class.java
        )
    )

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            ModalDrawerSheet {

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "My Athletic Coach",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        horizontal = 24.dp,
                        vertical = 16.dp
                    )
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                NavigationDrawerItem(
                    label = {
                        Text("Profile")
                    },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }

                        context.startActivity(
                            Intent(
                                context,
                                UserProfile::class.java
                            )
                        )
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Athletes")
                    },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }

                        context.startActivity(
                            Intent(
                                context,
                                Find_friends::class.java
                            )
                        )
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Workouts")
                    },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }

                        context.startActivity(
                            Intent(
                                context,
                                com.example.atc.Workouts.Workouts::class.java
                            )
                        )
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Map")
                    },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }

                        context.startActivity(
                            Intent(
                                context,
                                PermissionsActivity::class.java
                            )
                        )
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Videos")
                    },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }

                        context.startActivity(
                            Intent(
                                context,
                                VideoActivity::class.java
                            )
                        )
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Stopwatch")
                    },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }

                        context.startActivity(
                            Intent(
                                context,
                                Stopwatch::class.java
                            )
                        )
                    }
                )
            }
        }
    ) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {

                        Column {

                            Text(
                                text = "My Athletic Coach",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Athletic Management Application",
                                fontSize = 12.sp
                            )
                        }
                     },

                    navigationIcon = {

                        Text(
                            text = "☰",
                            fontSize = 28.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                        )
                    }
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                LazyVerticalGrid(

                    columns = GridCells.Fixed(2),

                    modifier = Modifier.fillMaxSize(),

                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    items(menuItems) { item ->

                        HomeCard(
                            item = item,
                            onClick = {

                                item.destination?.let { destination ->

                                    context.startActivity(
                                        Intent(
                                            context,
                                            destination
                                        )
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeCard(
    item: HomeMenuItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clickable(
                enabled = item.destination != null,
                onClick = onClick
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(
                    id = item.imageRes
                ),
                contentDescription = item.title,
                modifier = Modifier.size(90.dp)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = item.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}