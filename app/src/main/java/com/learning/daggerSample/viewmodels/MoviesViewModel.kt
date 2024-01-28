package com.learning.daggerSample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.daggerSample.models.Movie
import com.learning.daggerSample.repository.MoviesRepository
import com.learning.daggerSample.repository.ResponseType
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getMovies()
        }
    }

    val movies: LiveData<ResponseType<List<Movie>>>
        get() = repository.movies
}