package com.example.nasaneo.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaneo.di.AppModule.IO_SCHEDULER
import com.example.nasaneo.di.AppModule.UI_SCHEDULER
import com.example.nasaneo.domain.model.Event
import com.example.nasaneo.domain.usecase.GetFeedUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Named

class ListViewModel @Inject constructor(
    getFeedUseCase: GetFeedUseCase,
    @Named(IO_SCHEDULER) ioScheduler: Scheduler,
    @Named(UI_SCHEDULER) uiScheduler: Scheduler
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewState = MutableLiveData<ListViewState>()
    val viewState: LiveData<ListViewState>
        get() = _viewState

    private val _currentState = MutableLiveData<Event<ListItemState>>()
    val currentState: LiveData<Event<ListItemState>>
        get() = _currentState

    init {
        getFeedUseCase.build()
            .map { items ->
                items.map { item ->
                    ListItemState(
                        neo = item,
                        name = item.name,
                        url = item.url
                    )
                }
            }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = {
                    _viewState.value = ListViewState(items = it)
                }
            )
            .addTo(compositeDisposable)
    }

    fun onItemClicked(itemState: ListItemState) {
        _currentState.value = Event(itemState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
