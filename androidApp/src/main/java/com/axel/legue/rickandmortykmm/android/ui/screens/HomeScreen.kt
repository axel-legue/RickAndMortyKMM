package com.axel.legue.rickandmortykmm.android.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.axel.legue.rickandmortykmm.presentation.SharedCharactersPresenter
import com.axel.legue.rickandmortykmm.presentation.SharedEpisodesPresenter
import org.koin.androidx.compose.get

@Composable
fun HomeScreen(
    sharedCharactersPresenter: SharedCharactersPresenter = get(),
    sharedEpisodesPresenter: SharedEpisodesPresenter = get()
) {
    val characters = sharedCharactersPresenter.characters.collectAsState().value
    val episodes = sharedEpisodesPresenter.episodes.collectAsState().value

    Surface(color = MaterialTheme.colorScheme.surface) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp)
        ) {
            Categories()
            LazyRow {
                characters?.let {
                    items(it) { character ->
                        with(character) {
                            MovieCard(
                                modifier = Modifier.padding(horizontal = 12.dp),
                                title = name,
                                subTitle = species,
                                imageUrl = image
                            )
                        }
                    }
                }
            }
            LazyColumn {
                episodes?.let {
                    items(it) { episode ->
                        with(episode) {
                            EpisodeItem(
                                modifier = Modifier.padding(horizontal = 12.dp),
                                title = name,
                                date = airDate,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Categories(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 32.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Movies",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface)
        )
        Text(
            text = "TV Shows",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface)
        )
        Text(
            text = "Anime",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface)
        )
        Text(
            text = "My list",
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface)
        )
    }
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    imageUrl: String
) {
    Card(
        modifier = modifier
            .width(200.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(),
        border = null,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AsyncImage(
                    modifier = Modifier.size(200.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 20.dp),
                    text = subTitle,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}

@Composable
fun EpisodeItem(
    modifier: Modifier = Modifier,
    title: String,
    date: String
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(),
        border = null,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 20.dp),
                    text = date,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    )
}
