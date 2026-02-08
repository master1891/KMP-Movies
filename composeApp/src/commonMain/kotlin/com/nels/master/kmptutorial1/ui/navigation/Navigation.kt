package com.nels.master.kmptutorial1.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nels.master.kmptutorial1.movies
import com.nels.master.kmptutorial1.ui.screens.detail.DetailScreen
import com.nels.master.kmptutorial1.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen {
                navController.navigate(Routes.Detail.createRoute(it.id))
            }
        }


        composable(
            Routes.Detail.route,
            arguments = listOf(
                navArgument("idMovie") { type = NavType.IntType }
            )

        ) { backStackEntry ->

            val idMovie = backStackEntry.arguments?.getInt("idMovie") ?: 0
            val movie = movies.first { it.id == idMovie }

            DetailScreen(movie) {
                navController.popBackStack()
            }

        }

    }
}