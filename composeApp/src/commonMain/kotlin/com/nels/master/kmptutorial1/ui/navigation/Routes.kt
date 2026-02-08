package com.nels.master.kmptutorial1.ui.navigation

sealed class Routes(val route: String) {
    object Home : Routes("Home")
    object Detail : Routes("Detail/{idMovie}") {
        fun createRoute(idMovie: Int) = "Detail/$idMovie"
    }
}