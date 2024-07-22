package com.example.todolistmqtt.ui.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistmqtt.ui.feature.about.AboutRoute
import com.example.todolistmqtt.ui.feature.splash.SplashRoute
import com.example.todolistmqtt.ui.feature.task.TaskRoute

@Composable
fun NavigationRoute() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.Splash.name) {
        composable(route = AppScreen.Splash.name) {
            SplashRoute(navController = navController)
        }
        composable(route = AppScreen.Task.name) {
            TaskRoute(navController = navController)
        }
        composable(route = AppScreen.About.name) {
            AboutRoute(navController = navController)
        }
    }
}