package com.ihsanarslan.onboardingscreen.navigation

import kotlinx.serialization.Serializable

sealed class Router{

    @Serializable
    data object Onboarding : Router()

    @Serializable
    data object Home : Router()

}