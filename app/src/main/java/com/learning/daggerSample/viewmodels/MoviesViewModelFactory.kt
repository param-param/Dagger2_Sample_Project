package com.learning.daggerSample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.learning.daggerSample.repository.MoviesRepository
import javax.inject.Inject

class MoviesViewModelFactory @Inject constructor(private val repository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MoviesViewModel(repository) as T
    }
}