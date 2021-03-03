package com.volare.mojikore.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.volare.mojikore.model.Pokemon
import com.volare.mojikore.network.MojikoreClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mojikoreClient: MojikoreClient,
) : Repository {

    @WorkerThread
    suspend fun fetchList() {
        Log.v("main Repository", "called fetchList()")
        val res = mojikoreClient.fetchPokemonList(page = 0)
        res.suspendOnSuccess {
            data.whatIfNotNull { response ->
                var pokemons = response.results
                pokemons.forEach { pokemon -> pokemon.page = 0 }
            }
        }
    }

    @WorkerThread
    fun fetch(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>> {
        return flow {
            Log.v("main Repository", "called fetch()")
            val response = mojikoreClient.fetchPokemonList(page = 0)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    var pokemons = response.results
                    Log.v("main repository", "mainRepository pokemons: $pokemons")
                    pokemons.forEach { pokemon -> pokemon.page = 0 }
                    emit(pokemons)
                }
            }.onError {
                Log.v("main repository", "error")
                onError("error")
            }.onException {
                Log.v("main repository", "onException: $message")
                onError(message)
            }
        }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
    }
}