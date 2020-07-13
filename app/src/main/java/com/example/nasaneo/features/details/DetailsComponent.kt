package com.example.nasaneo.features.details

import dagger.Subcomponent

@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    val viewModelFactory: DetailsViewModel.Factory

}
