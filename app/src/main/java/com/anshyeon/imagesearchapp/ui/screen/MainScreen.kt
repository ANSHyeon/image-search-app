package com.anshyeon.imagesearchapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anshyeon.imagesearchapp.navigation.MainNavGraph
import com.anshyeon.imagesearchapp.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Box(Modifier.padding(it)) {
            MainNavGraph(
                navController = navController,
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isBottomBarDestination = TopLevelDestination.entries.any { it.route == currentRoute }
    if (isBottomBarDestination) {
        NavigationBar(
            modifier = Modifier,
            containerColor = Color.White
        ) {
            TopLevelDestination.entries.forEach { navItem ->
                val selected = currentRoute == navItem.route
                AddNavItem(navItem, navController, selected)
            }
        }
    }
}

@Composable
private fun RowScope.AddNavItem(
    navItem: TopLevelDestination,
    navController: NavHostController,
    selected: Boolean
) {
    NavigationBarItem(
        selected = selected,
        onClick = {
            navController.navigate(navItem.route) {
                navController.graph.startDestinationRoute?.let {
                    popUpTo(it) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = if (selected) navItem.selectedIcon else navItem.unselectedIcon),
                contentDescription = null,
                tint = Color.Black
            )
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.White
        )
    )
}