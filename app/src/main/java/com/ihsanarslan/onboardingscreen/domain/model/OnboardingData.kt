package com.ihsanarslan.onboardingscreen.domain.model

import android.net.Uri

data class OnboardingData(
    val title: String,
    val description: String,
    val videoUri: Uri
)