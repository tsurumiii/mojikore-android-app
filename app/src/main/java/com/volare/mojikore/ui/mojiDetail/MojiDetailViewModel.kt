package com.volare.mojikore.ui.mojiDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skydoves.pokedex.base.LiveCoroutinesViewModel
import com.volare.mojikore.model.Image
import com.volare.mojikore.model.Char
import javax.inject.Inject

class MojiDetailViewModel @Inject constructor() : LiveCoroutinesViewModel()  {

    private val _mojiListLiveData: MutableLiveData<List<String>> = MutableLiveData(listOf("", "", ""))
    val mojiListLiveData: LiveData<List<String>> get() = _mojiListLiveData

    private val _images: MutableLiveData<List<Image>> = MutableLiveData()
    val images: LiveData<List<Image>> get() = _images

    init {
        Log.v("MojiDetailViewModel", "activity view model: $mojiListLiveData")
        _mojiListLiveData.value =  List<String>(23) {"テキスト$it"}
        var imageList = Image(1, "2021/3/18(木) 21:00", 11.1, 11.1, "東京都〇〇区××××××××××××××", "https://i.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI", "あああ", List<Char>(1){Char(1, "あ", "2", "10%", "1")})
        _images.value = List<Image>(23) { imageList }
        Log.v("MojiDetailViewModel", "image: ${images.value}")
    }
}