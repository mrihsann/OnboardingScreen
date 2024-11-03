package com.ihsanarslan.onboardingscreen.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ihsanarslan.onboardingscreen.data.local.preferences.AppDataStore
import com.ihsanarslan.onboardingscreen.navigation.NavGraph
import com.ihsanarslan.onboardingscreen.ui.theme.OnboardingScreenTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnboardingScreenTheme {
                NavGraph()
            }
        }
    }
}