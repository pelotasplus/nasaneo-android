package com.example.nasaneo.di

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_NeoAssistedInjectModule::class])
abstract class NeoAssistedInjectModule
