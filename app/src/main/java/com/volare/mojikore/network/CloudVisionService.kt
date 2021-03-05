package com.volare.mojikore.network

import com.skydoves.sandwich.ApiResponse
import com.volare.mojikore.BuildConfig
import com.volare.mojikore.model.cloudVision.DetectCharsRequest
import com.volare.mojikore.model.cloudVision.DetectCharsResponse
import retrofit2.http.*

interface CloudVisionService {

    @Headers("Content-Type: application/json")
    @POST("./images:annotate")
    suspend fun detectChars(
        @Query("key") key: String = BuildConfig.CLOUD_VISION_API_KEY,
        @Body body: DetectCharsRequest
    ): ApiResponse<DetectCharsResponse>
}