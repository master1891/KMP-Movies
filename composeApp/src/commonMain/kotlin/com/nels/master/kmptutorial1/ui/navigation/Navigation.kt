package com.nels.master.kmptutorial1.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nels.master.kmptutorial1.data.MoviesService
import com.nels.master.kmptutorial1.data.movies
import com.nels.master.kmptutorial1.ui.screens.detail.DetailScreen
import com.nels.master.kmptutorial1.ui.screens.home.HomeScreen
import com.nels.master.kmptutorial1.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kmptutorial1.composeapp.generated.resources.Res
import kmptutorial1.composeapp.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val client = remember {
        HttpClient {
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    val key = stringResource(Res.string.api_key)
    val homeViewmodel = viewModel {
        HomeViewModel(MoviesService(key, client))
    }


    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(
                vm = homeViewmodel,
            ) {
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
            val movie = homeViewmodel.state.movies.first { it.id == idMovie }

            DetailScreen(movie) {
                navController.popBackStack()
            }

        }

    }
}