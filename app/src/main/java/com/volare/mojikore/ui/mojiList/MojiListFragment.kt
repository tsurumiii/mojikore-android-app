package com.volare.mojikore.ui.mojiList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MojiListFragmentBinding

class MojiListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: MojiListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.moji_list_fragment, container, false)


        return binding.root
    }
}