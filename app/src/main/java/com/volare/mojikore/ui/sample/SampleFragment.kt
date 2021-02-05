package com.volare.mojikore.ui.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.volare.mojikore.R

class SampleFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.sample_fragment, container, false)
        view.findViewById<Button>(R.id.home_button).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_homeFragment)
        }
        return view
    }
}