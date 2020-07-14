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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class ListViewModel @Inject constructor(
    getFeedUseCase: GetFeedUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val itemToOpen = MutableLiveData<Event<ListItemState>>()
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    viewState.value = ListViewState(items = it)
                }
            )
            .addTo(compositeDisposable)
    }

    fun onItemClicked(itemState: ListItemState) {
        itemToOpen.value = Event(itemState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
