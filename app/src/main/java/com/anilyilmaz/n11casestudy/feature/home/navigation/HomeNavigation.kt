package com.anilyilmaz.n11casestudy.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anilyilmaz.n11casestudy.feature.home.HomeRoute

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen() {
    composable(
        route = HOME_ROUTE
    ) {
        HomeRoute()
    }
}