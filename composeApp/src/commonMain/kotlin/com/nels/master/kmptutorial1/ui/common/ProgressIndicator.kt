package com.nels.master.kmptutorial1.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProgressIndicator(
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    if (enabled){
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            CircularProgressIndicator(
                color = ProgressIndicatorDefaults.circularColor,
                strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth
            )
        }
    }

}