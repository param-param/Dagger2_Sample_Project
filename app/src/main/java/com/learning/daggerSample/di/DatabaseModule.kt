package com.learning.daggerSample.di

import android.content.Context
import androidx.room.Room
import com.learning.daggerSample.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "movieDB").build()
    }
}