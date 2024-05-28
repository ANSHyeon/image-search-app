package com.anshyeon.imagesearchapp.navigation

import com.anshyeon.imagesearchapp.R

enum class TopLevelDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val route: String,
) {
    FEED(
        selectedIcon = R.drawable.ic_feed,
        unselectedIcon = R.drawable.ic_feed_outline,
        route = FEED_ROUTE,
    ),
    FAVORITES(
        selectedIcon = R.drawable.ic_favorite,
        unselectedIcon = R.drawable.ic_favorite_outline,
        route = BOOKMARKS_ROUTE,
    ),
}

const val FEED_ROUTE = "feed_route"
const val BOOKMARKS_ROUTE = "favorite_route"
