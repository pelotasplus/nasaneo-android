package com.example.nasaneo.features.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.nasaneo.di.AppModule.IO_SCHEDULER
import com.example.nasaneo.di.AppModule.UI_SCHEDULER
import com.example.nasaneo.domain.model.Event
import com.example.nasaneo.domain.usecase.GetFeedUseCase
import javax.inject.Inject
import javax.inject.Named

class ListViewModel @Inject constructor(
    getFeedUseCase: GetFeedUseCase,
    @Named(IO_SCHEDULER) ioScheduler: Scheduler,
    @Named(UI_SCHEDULER) uiScheduler: Scheduler
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val viewState = MutableLiveData<ListViewState>()

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
                    viewState.value = ListViewState(items = it)
                }
            )
            .addTo(compositeDisposable)
    }

    fun onItemClicked(itemState: ListItemState) {
        // handle 'on click' logic here
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
