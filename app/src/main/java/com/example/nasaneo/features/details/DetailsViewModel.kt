package com.example.nasaneo.features.details

import androidx.lifecycle.ViewModel
import com.example.nasaneo.data.model.Neo
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class DetailsViewModel @AssistedInject constructor(
    @Assisted neo: Neo
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(neo: Neo): DetailsViewModel
    }
}
