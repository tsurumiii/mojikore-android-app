package com.volare.mojikore.ui.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.volare.mojikore.R
import com.volare.mojikore.databinding.SampleFragmentBinding

class SampleFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: SampleFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.sample_fragment, container, false)

        binding.homeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_FirstFragment_to_homeFragment)
        }

        return binding.root
        
    }
}