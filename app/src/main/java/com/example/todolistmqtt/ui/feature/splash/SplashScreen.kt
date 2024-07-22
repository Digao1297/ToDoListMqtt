package com.example.todolistmqtt.ui.feature.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistmqtt.ui.feature.navigation.AppScreen

@Composable
fun SplashRoute(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    SplashScreen(uiState) {
        navController.navigate(AppScreen.Task.name)
    }
}

@Composable
private fun SplashScreen(
    uiState: State<SplashViewModel.UIState>,
    navigation: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState.value) {
            is SplashViewModel.UIState.Success -> {
                Text(text = "Success")
                navigation()
            }

            SplashViewModel.UIState.Error -> {
                Text(text = "Error")
            }

            SplashViewModel.UIState.Loading -> {
                CircularProgressIndicator()
                Text(text = "Sync")
            }
        }
    }
}

@Preview(showBackground = true, name = "SplashSuccess")
@Composable
private fun SuccessSplashPreview() {
    val uiState = remember { mutableStateOf(SplashViewModel.UIState.Success) }

    SplashScreen(uiState) {}
}

@Preview(showBackground = true, name = "SplashSync")
@Composable
private fun SyncSplashPreview() {
    val uiState = remember { mutableStateOf(SplashViewModel.UIState.Loading) }

    SplashScreen(uiState) {}
}

@Preview(showBackground = true, name = "SplashError")
@Composable
private fun ErrorSplashPreview() {
    val uiState = remember { mutableStateOf(SplashViewModel.UIState.Error) }

    SplashScreen(uiState) {}
}