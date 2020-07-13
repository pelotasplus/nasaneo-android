package com.example.nasaneo.features.list

import dagger.Subcomponent

@Subcomponent(modules = [ListModule::class])
interface ListComponent {

    val viewModel: ListViewModel

}
