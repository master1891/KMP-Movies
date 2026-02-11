package com.nels.master.kmptutorial1

import androidx.compose.ui.window.ComposeUIViewController
import com.nels.master.kmptutorial1.di.initkoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initkoin()
    }
) {
    App()
}