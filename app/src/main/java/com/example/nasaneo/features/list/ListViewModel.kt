package com.example.nasaneo.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaneo.data.INeoRepository
import com.example.nasaneo.data.model.Feed
import com.example.nasaneo.data.model.Neo
import com.example.nasaneo.domain.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val neoRepository: INeoRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

//    private val _feedLiveData = MutableLiveData<Feed>()
//    val feedLiveData: LiveData<Lis> = _feedLiveData

    val itemToOpen = MutableLiveData<Event<ListItemState>>()
    val viewState = MutableLiveData<ListViewState>()

    init {
        getFeed()
    }

    fun getFeed() {
        compositeDisposable.add(neoRepository.getFeed()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { getNeoItems(it.nearEarthObjects) }
            .map { mapNeoListToListViewState(it) }
            .subscribe({viewState.postValue(ListViewState(it))}, {}))
    }

    private fun mapNeoListToListViewState(list: List<Neo>): List<ListItemState> {
        return list.map { ListItemState(it, it.name, it.url) }
    }

    private fun getNeoItems(map: Map<String, List<Neo>>): List<Neo> {
        val list = mutableListOf<Neo>()
        map.onEach {
            list.addAll(it.value)
        }
        return list
    }

    fun onItemClicked(itemState: ListItemState) {
        itemToOpen.value = Event(itemState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
