package com.learning.daggerSample

import android.app.Application
import com.learning.daggerSample.di.ApplicationComponent
import com.learning.daggerSample.di.DaggerApplicationComponent


class MoviesApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }

}