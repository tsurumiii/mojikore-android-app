package com.volare.mojikore.di

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.volare.mojikore.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providerOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideMojikoreService(retrofit: Retrofit): MojikoreService {
        return retrofit.create(MojikoreService::class.java)
    }

    @Provides
    @Singleton
    fun providerMojikoreClient(mojikoreService: MojikoreService): MojikoreClient {
        return MojikoreClient(mojikoreService)
    }

    @Provides
    @Singleton
    fun provideCloudVisionService(retrofit: Retrofit): CloudVisionService {
        return retrofit.create(CloudVisionService::class.java)
    }

    @Provides
    @Singleton
    fun providerCloudVisionClient(cloudVisionService: CloudVisionService): CloudVisionClient {
        return CloudVisionClient(cloudVisionService)
    }
}