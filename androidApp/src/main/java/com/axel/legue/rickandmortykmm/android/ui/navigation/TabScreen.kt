package com.axel.legue.rickandmortykmm.android.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.axel.legue.rickandmortykmm.android.R

sealed class HomeTabScreen(
    override val route: String,
    @StringRes override val stringId: Int,
    @DrawableRes override val drawableId: Int
) : NavScreen {
    object Home : HomeTabScreen(
        route = "Home",
        stringId = R.string.menu_home,
        drawableId = R.drawable.ic_outline_home_24
    )

    object Favorites : HomeTabScreen(
        route = "Favorites",
        stringId = R.string.menu_favorites,
        drawableId = R.drawable.ic_baseline_favorite_border_24
    )

    object Settings : HomeTabScreen(
        route = "Settings",
        stringId = R.string.menu_settings,
        drawableId = R.drawable.ic_outline_settings_24
    )
}

sealed class ChildTabScreen(override val route: String) : Screen {
    object Details : ChildTabScreen(route = "Details")
}
