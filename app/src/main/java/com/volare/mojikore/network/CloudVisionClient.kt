package com.volare.mojikore.network

import android.util.Log
import com.skydoves.sandwich.ApiResponse
import com.squareup.moshi.*
import com.volare.mojikore.model.cloudVision.*
import javax.inject.Inject

class CloudVisionClient @Inject constructor(
    private val cloudVisionService: CloudVisionService
) {
    suspend fun detectChars(imgStr: String): ApiResponse<DetectCharsResponse> {
        return cloudVisionService.detectChars(
            body = DetectCharsRequest(listOf(Request(Image(imgStr))))
        )
    }
}