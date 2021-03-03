package com.volare.mojikore.ui.mojiRegister

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.volare.mojikore.R
import com.volare.mojikore.databinding.MojiRegisterFragmentBinding
import kotlinx.android.synthetic.main.moji_register_fragment.*

class MojiRegisterFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: MojiRegisterFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.moji_register_fragment, container, false)

        binding.mojiRegisterButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mojiRegisterFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imgBytes = Base64.decode(arguments?.getString("image"), 0)
        BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.size)?.let {
            image.setImageBitmap(it)
        }
    }
}