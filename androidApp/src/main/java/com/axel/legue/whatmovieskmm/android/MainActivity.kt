package com.axel.legue.whatmovieskmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.axel.legue.whatmovieskmm.Greeting

const val PURPLE_LIGHT = 0xFFBB86FC
const val PURPLE_DARK = 0xFF3700B3
const val GREEN = 0xFF03DAC5
const val PURPLE = 0xFF6200EE

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(PURPLE_LIGHT),
            primaryVariant = Color(PURPLE_DARK),
            secondary = Color(GREEN)
        )
    } else {
        lightColors(
            primary = Color(PURPLE_LIGHT),
            primaryVariant = Color(PURPLE),
            secondary = Color(GREEN)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(Greeting().greeting())
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Hello, Android!")
    }
}

@Composable
fun HomeNavigation() {
    val items = listOf(HomeTabScreen.Home, HomeTabScreen.Favorites, HomeTabScreen.Settings)
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    BottomNavigationItem(
                        icon = {
                            NavigationIcon(
                                iconRes = item.drawableId,
                                selected = selected
                            )
                        },
                        label = {
                            NavigationLabel(
                                labelRes = item.stringId,
                                selected = selected
                            )
                        },
                        selected = selected,
                        selectedContentColor = MaterialTheme.colors.primary,
                        unselectedContentColor = Color.LightGray,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
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
