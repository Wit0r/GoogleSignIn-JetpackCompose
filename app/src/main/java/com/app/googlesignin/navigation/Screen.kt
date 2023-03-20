package com.app.googlesignin.navigation

import com.app.googlesignin.core.Constants.LOGIN_SCREEN
import com.app.googlesignin.core.Constants.PROFILE_SCREEN

sealed class Screen(val route: String) {
    object LoginScreen: Screen(LOGIN_SCREEN)
    object ProfileScreen: Screen(PROFILE_SCREEN)
}
