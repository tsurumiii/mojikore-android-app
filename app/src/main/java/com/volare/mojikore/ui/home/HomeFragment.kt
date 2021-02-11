package com.volare.mojikore.ui.home


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.volare.mojikore.MainActivity
import com.volare.mojikore.R
import com.volare.mojikore.databinding.HomeFragmentBinding

class HomeFragment: Fragment() {
    private val CAMERA_REQUEST_CODE = 1
    private val CAMERA_PERMISSION_REQUEST_CODE = 2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: HomeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        binding.mojiListButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_mojiListFragment)
        }

        binding.launchCameraButton.setOnClickListener { view: View ->
            this.context?.packageManager?.let {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).resolveActivity(it)?.let {
                    if (checkCameraPermission()) {
                        takePicture()
                    } else {
                        grantCameraPermission()
                    }
                } ?: Toast.makeText(this.context, "camera app error", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun checkCameraPermission(): Boolean {
        return PackageManager.PERMISSION_GRANTED == activity?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.CAMERA) }
    }

    private fun grantCameraPermission() = activity?.let {
        ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePicture()
            }
        }
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
        }

        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}