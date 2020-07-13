package com.example.nasaneo.features.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import com.example.nasaneo.data.model.Neo

class DetailsViewModel @AssistedInject constructor(
    @Assisted neo: Neo
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val viewState = MutableLiveData<DetailsViewState>()

    init {
        viewState.value = DetailsViewState(
            name = "Name: ${neo.name}"
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(neo: Neo): DetailsViewModel
    }
}
