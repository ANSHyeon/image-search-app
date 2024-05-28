package com.anshyeon.imagesearchapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anshyeon.imagesearchapp.ui.screen.favoriteScreen.FavoriteScreen
import com.anshyeon.imagesearchapp.ui.screen.feedScreen.FeedScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = TopLevelDestination.FEED.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = TopLevelDestination.FEED.route) {
            FeedScreen()
        }
        composable(route = TopLevelDestination.FAVORITES.route) {
            FavoriteScreen()
        }
    }
}