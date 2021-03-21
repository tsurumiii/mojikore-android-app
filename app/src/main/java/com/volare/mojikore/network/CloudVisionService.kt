package com.volare.mojikore.network

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.volare.mojikore.BuildConfig
import com.volare.mojikore.model.cloudVision.DetectCharsRequest
import com.volare.mojikore.model.cloudVision.DetectCharsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://vision.googleapis.com/v1/"

private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpRequestInterceptor())
        .build()

private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
        .build()

interface CloudVisionService {

    @Headers("Content-Type: application/json")
    @POST("./images:annotate")
    suspend fun detectChars(
        @Query("key") key: String = BuildConfig.CLOUD_VISION_API_KEY,
        @Body body: DetectCharsRequest
    ): ApiResponse<DetectCharsResponse>
}

object CloudVisionApi {
    val cloudVisionService : CloudVisionService by lazy { retrofit.create(CloudVisionService::class.java) }
}