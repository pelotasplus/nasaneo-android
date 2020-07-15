package com.example.nasaneo.features.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaneo.data.NeoRepository
import com.example.nasaneo.data.model.Feed
import com.example.nasaneo.di.AppModule
import com.example.nasaneo.domain.model.Event
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class ListViewModel @Inject constructor(
    neoRepository: NeoRepository,
    @Named(AppModule.IO_SCHEDULER) ioScheduler: Scheduler,
    @Named(AppModule.UI_SCHEDULER) mainThreadScheduler: Scheduler
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
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
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
