package com.axel.legue.whatmovieskmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.axel.legue.whatmovieskmm.android.ui.navigation.HomeTabScreen
import com.axel.legue.whatmovieskmm.android.ui.navigation.NavigationIcon
import com.axel.legue.whatmovieskmm.android.ui.navigation.NavigationLabel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeNavigation()
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavigation() {
    val items = listOf(HomeTabScreen.Home, HomeTabScreen.Favorites, HomeTabScreen.Settings)
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.navigationBarsPadding(), bottomBar = {
        NavigationBar() {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEachIndexed { index, item ->
                val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                NavigationBarItem(selected = selected, label = {
                    NavigationLabel(
                        labelRes = item.stringId, selected = selected
                    )
                }, icon = {
                    NavigationIcon(
                        iconRes = item.drawableId, selected = selected
                    )
                }, onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
        }
    }) { innerPadding ->
        WhatMoviesNavHost(
            modifier = Modifier
                .padding(innerPadding)
                .navigationBarsPadding(),
            navController = navController
        )
    }
}

@Composable
private fun WhatMoviesNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeTabScreen.Home.route,
        modifier = modifier
    ) {
        composable(HomeTabScreen.Home.route) {
            Text(text = "Home")
        }
        composable(HomeTabScreen.Favorites.route) {
            Text(text = "Favorites")
        }
        composable(HomeTabScreen.Settings.route) {
            Text(text = "Settings")
        }
    }
}
