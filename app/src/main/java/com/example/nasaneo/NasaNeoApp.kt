package com.example.nasaneo

import android.app.Application
import com.example.nasaneo.di.AppComponent
import com.example.nasaneo.di.DaggerAppComponent
import com.example.nasaneo.di.InjectorProvider

class NasaNeoApp : Application(), InjectorProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

    }

    override fun appComponent() = appComponent
}
