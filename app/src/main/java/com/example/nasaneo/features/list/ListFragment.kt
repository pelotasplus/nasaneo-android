package com.example.nasaneo.features.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.nasaneo.databinding.ListFragmentBinding
import com.example.nasaneo.di.AppComponent
import com.example.nasaneo.di.getComponent
import com.example.nasaneo.di.viewModels
import javax.swing.text.View

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels {
        getComponent<AppComponent>()
            .plus(ListModule())
            .viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ListFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.itemToOpen.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
//            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
                findNavController().navigate(
                    ListFragmentDirections.actionListFragmentToDetailsFragment(it.neo)
                )
            }
        }
    }

}
