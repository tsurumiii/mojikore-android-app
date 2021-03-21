package com.volare.mojikore.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.skydoves.bindables.BindingActivity
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainActivity :  BindingActivity<MainActivityBinding>(R.layout.main_activity) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("main activity", "MainActivity vieModel: $viewModel")
        // Toolbarをセット
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        // UpIconを表示しないFragmentを設定する
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.SampleFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.visibility = if(destination.id == R.id.SampleFragment || destination.id == R.id.homeFragment) View.GONE else View.VISIBLE
            toolbar.setNavigationIcon(R.drawable.ic_back)
//           if (destination.id == R.id.mojiDetailFragment) {
//               supportActionBar?.title = arguments?.getString("あつめた文字")
//           }
        }

        binding {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }
    }

    override fun onSupportNavigateUp(): Boolean =
            findNavController(R.id.nav_host_fragment).navigateUp()
}