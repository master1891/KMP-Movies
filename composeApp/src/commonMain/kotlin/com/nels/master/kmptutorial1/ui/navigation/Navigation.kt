package com.nels.master.kmptutorial1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.nels.master.kmptutorial1.ui.screens.detail.DetailScreen
import com.nels.master.kmptutorial1.ui.screens.home.HomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen{
                navController.navigate("detail/${it.id}")
            }
        }
        composable("detail/{id}"){
            DetailScreen()
        }

    }
}