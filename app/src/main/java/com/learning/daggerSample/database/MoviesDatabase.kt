package com.learning.daggerSample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learning.daggerSample.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

}