package com.volare.mojikore.ui.mojiRegister

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.gms.location.*
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MojiRegisterFragmentBinding
import kotlinx.android.synthetic.main.moji_register_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MojiRegisterFragment : Fragment() {
    val viewModel: MojiRegisterViewModel by activityViewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var timeText: String
    private var locationText: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: MojiRegisterFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.moji_register_fragment, container, false)

        binding.mojiRegisterButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mojiRegisterFragment_to_homeFragment)
        }

        binding.registerChars.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                Log.v("main activity", "" + register_chars.text.length)
                if (3 < register_chars.text.length) {
                    register_chars.setBackgroundResource(R.drawable.edit_text_error_border)
                    error_text.visibility = View.VISIBLE
                    moji_register_disabled_button.visibility = View.VISIBLE
                    moji_register_button.visibility = View.INVISIBLE
                } else {
                    register_chars.setBackgroundResource(R.drawable.edit_text_border)
                    error_text.visibility = View.INVISIBLE
                    moji_register_disabled_button.visibility = View.INVISIBLE
                    moji_register_button.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imgStr = arguments?.getString("image") ?: ""
        val imgBytes = Base64.decode(imgStr, 0)
        BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.size)?.let {
            image.setImageBitmap(it)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cloudVisionRepository.detectTexts(imgStr).collect {
                register_chars.setText(it)
            }
        }
        val df = SimpleDateFormat("yyyy/M/d (E) H:m", Locale("ja"))
        this.timeText = "" + df.format(Date(System.currentTimeMillis()))
        time_text.text = this.timeText

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.context)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations){
                    val geocoder = Geocoder(this@MojiRegisterFragment.context, Locale("ja"))
                    val addressList: List<Address>? = try {
                        geocoder.getFromLocation(35.680219, 139.471581, 1)
                    } catch (e: IOException) {
                        return
                    }
                    if (this@MojiRegisterFragment.locationText === null) {
                        this@MojiRegisterFragment.locationText = "${addressList?.firstOrNull()?.getAddressLine(0)?.substring(13)}"
                        location_text.text = this@MojiRegisterFragment.locationText
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun startLocationUpdates() {
        val locationRequest = createLocationRequest() ?: return
        this.context?.let {
            if (ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun createLocationRequest(): LocationRequest? {
        return LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }
}