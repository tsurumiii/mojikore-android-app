package com.volare.mojikore.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :  BindingActivity<MainActivityBinding>(R.layout.main_activity) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("main activity", "MainActivity vieModel: $viewModel")
        binding {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }
    }
}