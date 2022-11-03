package com.axel.legue.rickandmortykmm.android.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun NavigationIcon(modifier: Modifier = Modifier, @DrawableRes iconRes: Int, selected: Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = iconRes),
        contentDescription = null,
        tint = if (selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun NavigationLabel(modifier: Modifier = Modifier, @StringRes labelRes: Int, selected: Boolean) {
    Text(
        modifier = modifier,
        text = stringResource(id = labelRes),
        style = MaterialTheme.typography.bodyMedium.copy(
            color = if (selected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground
        )
    )
}
