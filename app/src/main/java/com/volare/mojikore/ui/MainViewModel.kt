package com.volare.mojikore.ui

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.skydoves.bindables.bindingProperty
import com.skydoves.pokedex.base.LiveCoroutinesViewModel
import com.volare.mojikore.model.Pokemon
import com.volare.mojikore.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {
    private val _index: MutableLiveData<Int> = MutableLiveData(0)
    val index : LiveData<Int> get() = _index

    private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _pokemonListLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val pokemonListLiveData: LiveData<List<Pokemon>> get() = _pokemonListLiveData

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    init {
        viewModelScope.launch {
            mainRepository.fetch(
                onStart = { isLoading = true },
                onComplete = { isLoading = false },
                onError = { toastMessage = it }
            ).onStart {

            }.catch {

            }.collect {
                Log.v("main repository", "mainRepository: $it")
                _pokemonListLiveData.value = it
            }
        }
    }

    fun plus() {
        _index.value = index.value?.plus(1)
        Log.v("plusFunction", "activity view model: ${index.value}")
    }
}