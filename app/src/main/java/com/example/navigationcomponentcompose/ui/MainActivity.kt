package com.example.navigationcomponentcompose.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.navigationcomponentcompose.ui.theme.MyappTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val hometab = BottomItem("Home", Icons.Filled.Home, "home")
            val settings = BottomItem("Settings", Icons.Filled.Settings, "settings")
            val alerts = BottomItem("Alerts", Icons.Filled.Settings, "alerts")
            val items = listOf(hometab, settings, alerts)
            val navController = rememberNavController()
            MyappTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(bottomBar = { BottomItem(items, navController) }) {
                        NavHost(navController = navController, startDestination = hometab.route) {
                            composable(hometab.route) {
                                HomeTab(navController)
                            }
                            composable(settings.route) {
                                Settings(navController)
                            }
                            composable(alerts.route) {
                                Alerts(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomItem(items: List<BottomItem>, navController: NavHostController) {
    var selectedBottomItem by rememberSaveable { mutableStateOf(0) }
    NavigationBar {
        items.forEachIndexed { index, bottomItem ->
            NavigationBarItem(
                selected = selectedBottomItem == index,
                onClick = {
                    selectedBottomItem = index
                    navController.navigate(bottomItem.route)
                },
                icon = {
                    Icon(
                        imageVector = bottomItem.icon,
                        contentDescription = bottomItem.title
                    )
                },
                label = {
                    Text(text = bottomItem.title)
                }
            )
        }
    }
}






