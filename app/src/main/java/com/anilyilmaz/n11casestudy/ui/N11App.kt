package com.anilyilmaz.n11casestudy.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.anilyilmaz.n11casestudy.feature.home.navigation.HOME_ROUTE
import com.anilyilmaz.n11casestudy.feature.home.navigation.homeScreen

@Composable
fun N11App() {
    val navController = rememberNavController()

    Surface(
        Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { innerPadding ->
            N11NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                navController = navController
            )
        }
    }
}

@Composable
private fun N11NavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HOME_ROUTE
    ) {
        homeScreen()
    }
}