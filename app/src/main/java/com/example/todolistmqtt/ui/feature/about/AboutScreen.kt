package com.example.todolistmqtt.ui.feature.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun AboutRoute(
//    navController: NavController
) {
    AboutScreen()
}

@Composable
private fun AboutScreen() {

    Column(
        modifier =
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "About")
    }
}


@Preview(showBackground = true, name = "SplashSuccess")
@Composable
private fun AboutPreview() {
    AboutScreen()
}