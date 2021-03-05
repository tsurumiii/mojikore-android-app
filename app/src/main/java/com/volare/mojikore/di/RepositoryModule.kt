package com.volare.mojikore.di

import com.volare.mojikore.network.CloudVisionClient
import com.volare.mojikore.network.MojikoreClient
import com.volare.mojikore.repository.CloudVisionRepository
import com.volare.mojikore.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        mojikoreClient: MojikoreClient
    ): MainRepository {
        return MainRepository(mojikoreClient)
    }

    @Provides
    @ViewModelScoped
    fun provideCloudVisionRepository(
        cloudVisionClient: CloudVisionClient
    ): CloudVisionRepository {
        return CloudVisionRepository(cloudVisionClient)
    }
}