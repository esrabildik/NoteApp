package com.esrabildik.noteapp.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.esrabildik.noteapp.presentation.addNotePage.AddNoteUI
import com.esrabildik.noteapp.presentation.homePage.HomePageUI


@Composable
fun AppNavHost(){
    Surface(color = MaterialTheme.colorScheme.background){
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = NavScreen.HomePageUI.route)
        {
            composable(route = NavScreen.HomePageUI.route) {
                HomePageUI(navController = navController, LocalContext.current)
            }

            composable(
                route = NavScreen.AddNoteUI.route,
                arguments = listOf(navArgument(name = "noteID"){
                    type = NavType.IntType
                })
            ) { 
                AddNoteUI(navController = navController)
            }

        }
    }
}