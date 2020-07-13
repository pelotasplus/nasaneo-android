package com.example.nasaneo.features.list

import me.tatarka.bindingcollectionadapter2.ItemBinding
import com.example.nasaneo.BR
import com.example.nasaneo.R

data class ListViewState(
    val items: List<ListItemState>
) {
    fun getItemBinding(viewModel: ListViewModel) =
        ItemBinding.of<ListItemState>(BR.listItemState, R.layout.list_item_neo)
            .bindExtra(BR.viewModel, viewModel)
}
