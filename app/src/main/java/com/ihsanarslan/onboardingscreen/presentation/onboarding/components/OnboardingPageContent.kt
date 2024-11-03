package com.ihsanarslan.onboardingscreen.presentation.onboarding.components

import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ihsanarslan.onboardingscreen.domain.model.OnboardingData

@Composable
fun OnboardingPageContent(pageData: OnboardingData) {

    Column(modifier = Modifier
        .fillMaxSize()){
        Spacer(modifier = Modifier.height(100.dp))
        Box (modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(MaterialTheme.shapes.small)
        ){
            VideoPlayer(pageData.videoUri)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(modifier = Modifier
            .padding(35.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(textAlign = TextAlign.Center, text = pageData.title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(20.dp))
            Text(textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge,text = pageData.description)
        }
    }
}

@Composable
fun VideoPlayer(videoUri : Uri) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            VideoView(context).apply {
                setVideoURI(videoUri)
                setOnPreparedListener { mediaPlayer ->
                    mediaPlayer.isLooping = true
                }
                start()
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}
