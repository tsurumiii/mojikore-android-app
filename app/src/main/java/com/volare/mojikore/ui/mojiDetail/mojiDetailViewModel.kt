package com.volare.mojikore.ui.mojiDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MojiDetailViewModel(): ViewModel() {

    private val _mojiListLiveData: MutableLiveData<Array<String>> = MutableLiveData()
    val mojiListLiveData: LiveData<Array<String>> get() = _mojiListLiveData

    init {
        _mojiListLiveData.value =  Array<String>(23) {"テキスト$it"}
    }
}