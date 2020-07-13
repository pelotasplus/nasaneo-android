package com.example.nasaneo.domain.usecase

import io.reactivex.Single
import com.example.nasaneo.data.NeoRepository
import com.example.nasaneo.data.model.Neo
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    private val neoRepository: NeoRepository
) {
    fun build(): Single<List<Neo>> =
        neoRepository.getFeed()
            .map { it.nearEarthObjects.values.first() } // XXX

}
