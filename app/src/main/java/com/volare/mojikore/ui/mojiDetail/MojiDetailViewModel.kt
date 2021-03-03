package com.volare.mojikore.ui.mojiDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skydoves.pokedex.base.LiveCoroutinesViewModel
import javax.inject.Inject

class MojiDetailViewModel @Inject constructor() : LiveCoroutinesViewModel()  {

    private val _mojiListLiveData: MutableLiveData<List<String>> = MutableLiveData(listOf("", "", ""))
    val mojiListLiveData: LiveData<List<String>> get() = _mojiListLiveData

    init {
        Log.v("MojiDetailViewModel", "activity view model: $mojiListLiveData")
        _mojiListLiveData.value =  List<String>(23) {"テキスト$it"}
    }
}