package com.example.todolistmqtt.ui.feature.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistmqtt.R
import com.example.todolistmqtt.domain.usecase.ui.SyncStatus
import com.example.todolistmqtt.ui.feature.about.AboutRoute
import com.example.todolistmqtt.ui.feature.splash.SplashRoute
import com.example.todolistmqtt.ui.feature.task.TaskRoute

@Composable
fun NavigationRoute() {
    val context = LocalContext.current

    val syncStatusTaskViewModel: SyncStatusTaskViewModel = viewModel()


    LaunchedEffect(Unit) {
        syncStatusTaskViewModel.statusFlow.collect { state ->
            val message = when (state) {
                SyncStatus.CompletedSync -> R.string.complete_sync_message
                is SyncStatus.Error -> R.string.error_sync_message
                SyncStatus.NothingSync -> null
                SyncStatus.StartSync -> R.string.start_sync_message
                SyncStatus.SendSync -> R.string.send_sync_message
            }
            message?.let {
                Toast.makeText(context,it, Toast.LENGTH_LONG).show()
            }
        }
    }

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.Splash.name) {
        composable(route = AppScreen.Splash.name) {
            SplashRoute(navController = navController)
        }
        composable(route = AppScreen.Task.name) {
            TaskRoute(/*navController = navController*/)
        }
        composable(route = AppScreen.About.name) {
            AboutRoute(/*navController = navController*/)
        }
    }
}