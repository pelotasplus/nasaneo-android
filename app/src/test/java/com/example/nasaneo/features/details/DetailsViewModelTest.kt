package com.example.nasaneo.features.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nasaneo.data.model.Neo
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `should set view state`() {
        val neo = Neo("id", "refid", "name", "url")
        val viewModel = DetailsViewModel(neo)

        assertThat(viewModel.viewState.value!!.name).isEqualTo("Name: name")
    }

}
