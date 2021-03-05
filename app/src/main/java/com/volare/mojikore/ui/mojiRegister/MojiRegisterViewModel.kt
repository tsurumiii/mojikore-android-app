package com.volare.mojikore.ui.mojiRegister

import com.skydoves.pokedex.base.LiveCoroutinesViewModel
import com.volare.mojikore.repository.CloudVisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MojiRegisterViewModel @Inject constructor(
        public val cloudVisionRepository: CloudVisionRepository
) : LiveCoroutinesViewModel()  {
}