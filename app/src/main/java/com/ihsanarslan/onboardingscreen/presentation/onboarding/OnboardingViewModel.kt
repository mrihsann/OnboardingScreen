package com.ihsanarslan.onboardingscreen.presentation.onboarding

import android.app.Activity
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihsanarslan.onboardingscreen.data.local.preferences.AppDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appDataStore: AppDataStore
) : ViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState : StateFlow<UiState>
        get() = _uiState.asStateFlow()


    init {
        checkOnboardingStatus()
    }

    fun onEvent(event: UiEvent){
        when(event){
            is UiEvent.CompleteOnboarding -> completeOnboarding()
            is UiEvent.RequestNotificationPermission -> requestNotificationPermission(event.activity)
        }
    }

    private fun completeOnboarding() {
        viewModelScope.launch {
            appDataStore.setOnboarding(true)
            _uiState.update { it.copy(isOnboardingComplete = true) }
        }
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            appDataStore.isOnboarding().collect { isComplete ->
                _uiState.update { it.copy(isOnboardingComplete = isComplete) }
            }
        }
    }


    private fun requestNotificationPermission(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }
}