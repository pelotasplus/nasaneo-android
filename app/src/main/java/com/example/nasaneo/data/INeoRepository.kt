package com.example.nasaneo.data

import com.example.nasaneo.data.model.Feed
import io.reactivex.Single

interface INeoRepository {
    fun getFeed(): Single<Feed>
}