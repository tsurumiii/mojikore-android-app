package com.volare.mojikore.ui.mojiDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MojiDetailFragmentBinding

class MojiDetailFragment: Fragment() {
    private lateinit var viewModel: MojiDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: MojiDetailFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.moji_detail_fragment, container, false)

        viewModel = ViewModelProvider(this).get(MojiDetailViewModel::class.java)

        val list = viewModel.mojiListLiveData.value as Array<String>

        val adapter =  MojiDetailAdapter(list)
        val layoutManager = GridLayoutManager(activity, 2)

        binding.mojiDetailList.layoutManager =  layoutManager
        binding.mojiDetailList.setHasFixedSize(true)
        binding.mojiDetailList.adapter = adapter
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}