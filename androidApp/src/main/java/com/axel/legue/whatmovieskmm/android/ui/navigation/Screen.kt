package com.axel.legue.whatmovieskmm.android.ui.navigation

interface Screen {
    val route: String
}

interface NavScreen : Screen {
    val stringId: Int
    val drawableId: Int
}
