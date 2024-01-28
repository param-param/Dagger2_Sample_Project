package com.learning.daggerSample.di

import android.content.Context
import com.learning.daggerSample.activities.MoviesActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun inject(moviesActivity: MoviesActivity)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}