package com.volare.mojikore.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.volare.mojikore.persistence.AppDatabase
import com.volare.mojikore.persistence.TypeResponseConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
            application: Application,
            typeResponseConverter: TypeResponseConverter
    ): AppDatabase {
        return Room
                .databaseBuilder(application, AppDatabase::class.java, "Mojikore.db")
                .fallbackToDestructiveMigration()
                .addTypeConverter(typeResponseConverter)
                .build()
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
        return TypeResponseConverter(moshi)
    }
}