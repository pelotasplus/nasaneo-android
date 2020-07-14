package com.example.nasaneo.data

import io.reactivex.Single
import javax.inject.Inject

class NeoRepository @Inject constructor(
    private val nasaNeoApi: NasaNeoApi
) {
    fun getFeed(): Single<Unit> =
        Single.never()
}
