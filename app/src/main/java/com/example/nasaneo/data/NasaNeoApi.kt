package com.example.nasaneo.data

import io.reactivex.Single
import com.example.nasaneo.data.model.Feed
import retrofit2.http.GET

interface NasaNeoApi {

    @GET("/neo/rest/v1/feed/today?detailed=true&api_key=DEMO_KEY")
    fun getFeed(): Single<Feed>

}
