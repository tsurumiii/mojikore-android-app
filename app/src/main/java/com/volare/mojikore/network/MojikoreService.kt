package com.volare.mojikore.network

import com.skydoves.sandwich.ApiResponse
import com.volare.mojikore.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MojikoreService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<PokemonResponse>
}