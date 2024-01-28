package com.learning.daggerSample.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learning.daggerSample.api.MoviesService
import com.learning.daggerSample.database.MoviesDatabase
import com.learning.daggerSample.models.Movie
import com.learning.daggerSample.utils.Constants
import com.learning.daggerSample.utils.NetworkUtils
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val moviesService: MoviesService,
    private val moviesDatabase: MoviesDatabase,
    private val applicationContext: Context
) {

    private val _moviesLiveData = MutableLiveData<ResponseType<List<Movie>>>()

    val movies: LiveData<ResponseType<List<Movie>>>
        get() = _moviesLiveData

    suspend fun getMovies() {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            try {
                val response = moviesService.getUpcomingMoviesList(Constants.API_KEY, 1)
                if (response.body() != null) {
                    val results: List<Movie> = response.body()!!.results
                    for (movie in results) {
                        val movieDb: Movie = moviesDatabase.getMoviesDao().getMovieById(movie.id)
                        if (movieDb != null) {
                            moviesDatabase.getMoviesDao().updateMovie(movie)
                        } else {
                            moviesDatabase.getMoviesDao().insertMovie(movie)
                        }
                    }
                    _moviesLiveData.postValue(ResponseType.Success(results))
                } else {
                    _moviesLiveData.postValue(ResponseType.Error("Api error"))
                }
            } catch (e: Exception) {
                _moviesLiveData.postValue(ResponseType.Error(e.message.toString()))
            }
        } else {
            try {
                val movies = moviesDatabase.getMoviesDao().getMovies()
                _moviesLiveData.postValue(ResponseType.Success(movies))
            } catch (e: Exception) {
                _moviesLiveData.postValue(ResponseType.Error(e.message.toString()))
            }

        }

    }
}











