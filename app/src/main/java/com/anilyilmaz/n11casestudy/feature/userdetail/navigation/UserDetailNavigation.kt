package com.anilyilmaz.n11casestudy.feature.userdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anilyilmaz.n11casestudy.feature.userdetail.UserDetailRoute

internal const val USER_NAME = "username"

fun NavController.navigateToUserDetail(username: String?, navOptions: NavOptions) {
    navigate("userDetail/$username", navOptions)
}

fun NavGraphBuilder.userDetailScreen(
    onNavigationClick: () -> Unit
) {
    composable(
        route = "userDetail/{$USER_NAME}",
        arguments = listOf(navArgument(USER_NAME) {
            defaultValue = ""
            type = NavType.StringType
        })
    )
    {
        UserDetailRoute(
            onNavigationClick = {
                onNavigationClick()
            }
        )
    }
}