package com.ihsanarslan.onboardingscreen.presentation.onboarding

import android.app.Activity

sealed class UiEvent{
    data object CompleteOnboarding : UiEvent()
    data class RequestNotificationPermission(val activity: Activity) : UiEvent()
}