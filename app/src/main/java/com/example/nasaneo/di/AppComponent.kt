package com.example.nasaneo.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import com.example.nasaneo.features.details.DetailsComponent
import com.example.nasaneo.features.details.DetailsModule
import com.example.nasaneo.features.list.ListComponent
import com.example.nasaneo.features.list.ListModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NeoAssistedInjectModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun plus(module: ListModule): ListComponent

    fun plus(module: DetailsModule): DetailsComponent

}
