package com.nels.master.kmptutorial1.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nels.master.kmptutorial1.BuildConfig
import com.nels.master.kmptutorial1.data.MoviesRepository
import com.nels.master.kmptutorial1.data.MoviesService
import com.nels.master.kmptutorial1.data.database.MoviesDao
import com.nels.master.kmptutorial1.ui.screens.detail.DetailScreen
import com.nels.master.kmptutorial1.ui.screens.detail.DetailViewModel
import com.nels.master.kmptutorial1.ui.screens.home.HomeScreen
import com.nels.master.kmptutorial1.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.http.URLProtocol.Companion.HTTPS
import io.ktor.http.parameters
import io.ktor.http.parametersOf
import io.ktor.serialization.kotlinx.json.json
import kmptutorial1.composeapp.generated.resources.Res
import kmptutorial1.composeapp.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation() {
    val navController = rememberNavController()




    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(
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


            DetailScreen(
                vm = koinViewModel(parameters = { parametersOf(idMovie) })
            ) {
                navController.popBackStack()
            }
        }

    }
}


