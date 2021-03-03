package com.volare.mojikore.network

import android.util.Log
import com.skydoves.sandwich.ApiResponse
import com.volare.mojikore.model.PokemonResponse
import javax.inject.Inject

class MojikoreClient @Inject constructor(
    private val mojikoreService: MojikoreService
) {

    suspend fun fetchPokemonList(
        page: Int
    ): ApiResponse<PokemonResponse> {
        Log.v("main MojikoreClient", "called fetchPokemonList()")
        return mojikoreService.fetchPokemonList(
            limit = PAGING_SIZE,
            offset = page * PAGING_SIZE
        )
    }

    companion object {
        private const val PAGING_SIZE = 20
    }
}