package com.example.nasaneo.features.list

import com.example.nasaneo.data.model.Neo
import com.example.nasaneo.domain.usecase.GetFeedUseCase
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test

class ListViewModelTest {

    private val neo = Neo("id", "refid", "name", "url")
    private val getFeedUseCase = mock<GetFeedUseCase>()

    @Test
    fun `should fetch items`() {
        // given

        // when
        val viewModel = createViewModel()

        // then
    }

    private fun createViewModel() =
        ListViewModel(getFeedUseCase)
}
