package com.volare.mojikore.ui.mojiDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MojiDetailFragmentBinding
import com.volare.mojikore.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MojiDetailFragment: Fragment()  {

    @VisibleForTesting
    val viewModel: MojiDetailViewModel by activityViewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)


        Log.v("main activity", "MojiDetailActivity viewModel: $mainViewModel")

        val binding: MojiDetailFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.moji_detail_fragment, container, false
        )

        binding.vm = viewModel
        binding.adapter = MojiDetailAdapter()
        binding.mainVm = mainViewModel
        return binding.root;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}