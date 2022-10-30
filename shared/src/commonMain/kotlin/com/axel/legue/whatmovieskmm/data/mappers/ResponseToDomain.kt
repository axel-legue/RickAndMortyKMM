package com.axel.legue.whatmovieskmm.data.mappers

import com.axel.legue.whatmovieskmm.data.network.models.UpcomingMoviesResponse
import com.axel.legue.whatmovieskmm.domain.models.Movie

fun UpcomingMoviesResponse.upcomingMovieResponseToMovies(): List<Movie> = results.map { result ->
    with(result) {
        Movie(
            adult = adult,
            backdropPath = backdropPath,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}
