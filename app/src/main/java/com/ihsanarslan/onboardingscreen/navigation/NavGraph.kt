package com.ihsanarslan.onboardingscreen.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ihsanarslan.onboardingscreen.presentation.home.HomeScreen
import com.ihsanarslan.onboardingscreen.presentation.onboarding.OnBoardingScreen
import com.ihsanarslan.onboardingscreen.presentation.onboarding.OnboardingViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val vm = hiltViewModel<OnboardingViewModel>()
    val isOnboardingComplete = vm.uiState.collectAsStateWithLifecycle().value.isOnboardingComplete

    NavHost(navController = navController,
        startDestination = if (isOnboardingComplete) Router.Home else Router.Onboarding
    ) {
        composable<Router.Onboarding> {
            val viewModel = hiltViewModel<OnboardingViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val onEvent = viewModel::onEvent
            OnBoardingScreen(uiState = uiState.value, onEvent = onEvent)
        }

        composable<Router.Home> {
            HomeScreen()
        }
    }
}