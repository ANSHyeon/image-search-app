package com.anshyeon.imagesearchapp.di

import android.content.Context
import androidx.room.Room
import com.anshyeon.imagesearchapp.data.local.dao.ImageDao
import com.anshyeon.imagesearchapp.data.local.ImageDatabase
import com.anshyeon.imagesearchapp.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ImageDatabase {
        return Room.databaseBuilder(
            context,
            ImageDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providerImagesDao(appDatabase: ImageDatabase): ImageDao {
        return appDatabase.imageDao()
    }
}