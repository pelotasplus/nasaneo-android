package com.example.nasaneo.features.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaneo.data.NeoRepository
import com.example.nasaneo.data.model.Feed
import com.example.nasaneo.domain.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor(
    neoRepository: NeoRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val itemToOpen = MutableLiveData<Event<ListItemState>>()

    val viewState = MutableLiveData<ListViewState>()

    init {
        val disposable = neoRepository
            .getFeed()
            .map { feed: Feed ->
                feed.nearEarthObjects.values.first()
            }
            .map { items -> items.map { item -> ListItemState(item, item.name, item.url) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { listItemStates ->
                viewState.value = ListViewState(listItemStates)
            }

        compositeDisposable.add(disposable)
    }

    fun onItemClicked(itemState: ListItemState) {
        itemToOpen.value = Event(itemState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
