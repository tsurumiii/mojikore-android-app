package com.volare.mojikore.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.skydoves.bindables.BindingActivity
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*

@AndroidEntryPoint
class MainActivity :  BindingActivity<MainActivityBinding>(R.layout.main_activity) {

    private val PERMISSION_REQUEST_CODE = 1234
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v("main activity", "MainActivity vieModel: $viewModel")

        requestPermission()

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

    private fun requestPermission() {
        val permissionAccessCoarseLocationApproved = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        if (!permissionAccessCoarseLocationApproved) {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                PERMISSION_REQUEST_CODE
            )
        }
    }
}