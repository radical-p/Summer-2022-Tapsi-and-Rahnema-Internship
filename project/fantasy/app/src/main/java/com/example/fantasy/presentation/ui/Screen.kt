package com.example.fantasy.presentation.ui


sealed class Screen(val route : String){
    object SignIn : Screen("SignIn")
    object SignUp : Screen("SignUp")
    object Verify : Screen("Verification")
    object Home : Screen("Home")
    object Feed : Screen("FeedPage")
    object Profile: Screen("ProfilePage")
}
