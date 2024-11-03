package com.ihsanarslan.onboardingscreen.presentation.onboarding

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ihsanarslan.onboardingscreen.domain.model.OnboardingData
import com.ihsanarslan.onboardingscreen.presentation.onboarding.components.NotificationPermissionOnboardingPage
import com.ihsanarslan.onboardingscreen.presentation.onboarding.components.OnboardingBottomBar
import com.ihsanarslan.onboardingscreen.presentation.onboarding.components.OnboardingPageContent
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    uiState: UiState,
    onEvent: (UiEvent) -> Unit
) {

    val context = LocalContext.current

    val videoUri1 = Uri.parse("android.resource://${context.packageName}/raw/onboarding")
    val videoUri2 = Uri.parse("android.resource://${context.packageName}/raw/onboarding2")
    val videoUri3 = Uri.parse("android.resource://${context.packageName}/raw/onboarding3")
    val videoUri4 = Uri.parse("android.resource://${context.packageName}/raw/onboarding4")

    val pages = listOf(
        OnboardingData(
            title = "Discover the Power of Test Analysis",
            description = "See your weaknesses and improve your strengths quickly by analyzing each test you've taken in detail. Support is with you every step of the way to success!",
            videoUri = videoUri1
        ),
        OnboardingData(
            title = "Advanced Comparison Features",
            description = "Analyze your performance in detail by filtering all your tests according to your preferred criteria. Evaluate your past performance and aim to improve with every test.",
            videoUri = videoUri2
        ),
        OnboardingData(
            title = "Visualize Your Progress with Graphs",
            description = "Visualize each test with customizable charts. See your net, correct, and incorrect counts at a glance and track your development.",
            videoUri = videoUri3
        ),
        OnboardingData(
            title = "Start Your Journey to Success",
            description = "With all these analysis and comparison features, achieving success is now easier. If you're ready to discover and improve yourself, let's get started!",
            videoUri = videoUri4
        )
    )

    val pagerState = rememberPagerState{pages.size+1}
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            OnboardingBottomBar(
                pageSize = pagerState.pageCount,
                pagerState = pagerState,
                onNextClicked = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < pagerState.pageCount - 1) {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        } else {
                            onEvent(UiEvent.CompleteOnboarding)
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            when(page){
                4 -> NotificationPermissionOnboardingPage(){
                    onEvent(UiEvent.RequestNotificationPermission(context as Activity))
                }
                else -> OnboardingPageContent(pageData = pages[page])
            }
        }
    }
}