package com.app.googlesignin.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.googlesignin.core.Constants.LOGIN_SCREEN
import com.app.googlesignin.core.Constants.PROFILE_SCREEN
import com.app.googlesignin.presentation.home.HomeScreen
import com.app.googlesignin.presentation.login.LoginScreen

@Composable
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
        composable(LOGIN_SCREEN) {
            LoginScreen(
                navigateToProfileScreen = {
                    navController.navigate(PROFILE_SCREEN)
                }
            )
        }
        composable(PROFILE_SCREEN) {
            HomeScreen()
        }
    }
}