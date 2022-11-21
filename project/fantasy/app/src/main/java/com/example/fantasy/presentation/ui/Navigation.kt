package com.example.fantasy.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fantasy.presentation.ui.FirstPageInitial
import com.example.fantasy.presentation.ui.FirstPage

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.SignIn.route) {
        composable(route = Screen.SignIn.route) {
            SignInPage(navController)
        }
        composable(route = Screen.SignUp.route) {
            SignUp(navController)
        }
        composable(route = Screen.Verify.route) {
            ConfirmationPage(navController)
        }
        composable(route = Screen.Home.route) {
            FirstPageInitial(navController)
        }
        composable(route = Screen.Feed.route) {
            FeedPage(navController)
        }
        composable(route = Screen.Profile.route) {
            ProfilePage(navController)
        }
    }
}