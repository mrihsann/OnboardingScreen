package com.ihsanarslan.onboardingscreen.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){
    Box (modifier = Modifier.fillMaxSize()){
        Text(
            text = "Home Screen",
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.titleLarge
            )
    }
}