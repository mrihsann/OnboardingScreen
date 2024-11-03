package com.ihsanarslan.onboardingscreen.presentation.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingBottomBar(
    pageSize: Int,
    pagerState: PagerState,
    onNextClicked: () -> Unit
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val buttonMinWidth = screenWidth / 2

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OnboardingIndicator(pageSize = pageSize, pagerState = pagerState)

        Button(modifier = Modifier.defaultMinSize(minWidth = buttonMinWidth-16.dp),onClick = onNextClicked, shape = MaterialTheme.shapes.small) {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = when(pagerState.currentPage){
                    0 -> "Let's start!"
                    pagerState.pageCount-1 -> "Close"
                    else -> "Next"
                }
            )
        }
    }
}