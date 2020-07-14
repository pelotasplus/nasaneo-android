package com.example.nasaneo.data

import io.reactivex.Single
import com.example.nasaneo.data.model.Feed
import javax.inject.Inject

class NeoRepository @Inject constructor(
    private val nasaNeoApi: NasaNeoApi
) {
    fun getFeed(): Single<Feed> =
        nasaNeoApi.getFeed()
}
