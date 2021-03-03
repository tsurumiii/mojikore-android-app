package com.volare.mojikore.ui.sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.volare.mojikore.R
import com.volare.mojikore.databinding.SampleFragmentBinding
import com.volare.mojikore.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleFragment: Fragment() {

    @VisibleForTesting
    val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v("main activity", "SampleFragment viewModel: $mainViewModel")
        val binding: SampleFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.sample_fragment, container, false)

        binding.homeButton.setOnClickListener { view: View ->
            mainViewModel.plus()
            view.findNavController().navigate(R.id.action_FirstFragment_to_homeFragment)
        }

        binding.mojiDetailButton.setOnClickListener { view: View ->
            mainViewModel.plus()
            view.findNavController().navigate(R.id.action_FirstFragment_to_mojiDetailFragment)
        }

        return binding.root
    }
}