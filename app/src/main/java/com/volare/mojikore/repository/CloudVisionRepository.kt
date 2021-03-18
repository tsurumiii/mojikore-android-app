package com.volare.mojikore.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.volare.mojikore.model.Pokemon
import com.volare.mojikore.network.CloudVisionClient
import com.volare.mojikore.network.MojikoreClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CloudVisionRepository @Inject constructor(
    private val cloudVisionClient: CloudVisionClient,
) : Repository {

    @WorkerThread
    fun detectTexts(
        imgStr: String
    ): Flow<String> {
        return flow {
            val response = cloudVisionClient.detectChars(imgStr)
            response.suspendOnSuccess {
                Log.v("main repository", "suspendOnSuccess")
                data.whatIfNotNull { response ->
                    emit("" + response.responses[0].textAnnotations[0].description.replace("[\n\r]".toRegex(), ""))
                }
            }.onError {
                Log.v("main repository", message())
            }.onException {
                Log.v("main repository", "onException: $message")
            }
        }.onStart {  }.onCompletion {  }.flowOn(Dispatchers.IO)
    }
}