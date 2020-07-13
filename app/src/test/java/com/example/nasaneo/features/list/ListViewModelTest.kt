package com.example.nasaneo.features.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nasaneo.data.model.Neo
import com.example.nasaneo.domain.usecase.GetFeedUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getFeedUseCase = mock<GetFeedUseCase>()
    private val testScheduler = TestScheduler()

    @Test
    fun `should fetch items`() {
        val neo = Neo("id", "refid", "name", "url")
        whenever(getFeedUseCase.build())
            .thenReturn(Single.just(listOf(neo)))

        val viewModel = createViewModel()
        testScheduler.triggerActions()

        assertThat(viewModel.viewState.value!!.items)
            .isEqualTo(listOf(ListItemState(neo = neo, name = "name", url = "url")))
    }

    private fun createViewModel() =
        ListViewModel(
            getFeedUseCase,
            testScheduler,
            testScheduler
        )
}
