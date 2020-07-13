package com.example.nasaneo.features.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.nasaneo.databinding.DetailsFragmentBinding
import com.example.nasaneo.di.AppComponent
import com.example.nasaneo.di.getComponent
import com.example.nasaneo.di.viewModels

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels {
        getComponent<AppComponent>()
            .plus(DetailsModule())
            .viewModelFactory
            .create(args.neo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DetailsFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}
