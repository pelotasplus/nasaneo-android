package com.example.nasaneo.features.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaneo.domain.model.Event
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListViewModel @Inject constructor(
    // feel free to inject required dependencies here
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val itemToOpen = MutableLiveData<Event<ListItemState>>()
    val viewState = MutableLiveData<ListViewState>()

    init {
        // put your code fetching data here
    }

    fun onItemClicked(itemState: ListItemState) {
        itemToOpen.value = Event(itemState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
