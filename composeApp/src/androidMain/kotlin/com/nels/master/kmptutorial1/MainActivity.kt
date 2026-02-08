package com.nels.master.kmptutorial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ValidateDarkmode()
            App()
        }
    }
}

@Composable
fun ValidateDarkmode() {
    val darkMode = isSystemInDarkTheme()

    val view = LocalView.current
    val window = (view.context as ComponentActivity).window
    window.statusBarColor = Color.Transparent.toArgb()
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
        !darkMode
}


