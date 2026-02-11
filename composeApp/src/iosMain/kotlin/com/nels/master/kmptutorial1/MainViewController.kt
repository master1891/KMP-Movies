package com.nels.master.kmptutorial1

import androidx.compose.ui.window.ComposeUIViewController
import com.nels.master.kmptutorial1.data.database.getDatabaseBuider

fun MainViewController() = ComposeUIViewController {
    val database = getDatabaseBuider()
    App(database.moviesDao())
}