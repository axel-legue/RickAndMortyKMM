package com.axel.legue.whatmovieskmm.android.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun NavigationIcon(modifier: Modifier = Modifier, @DrawableRes iconRes: Int, selected: Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = iconRes),
        contentDescription = null,
        tint = if (selected) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground
    )
}

@Composable
fun NavigationLabel(modifier: Modifier = Modifier, @StringRes labelRes: Int, selected: Boolean) {
    Text(
        modifier = modifier,
        text = stringResource(id = labelRes),
        style = MaterialTheme.typography.body2.copy(
            color = if (selected) MaterialTheme.colors.secondary else MaterialTheme.colors.onBackground,
            fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal
        )
    )
}
