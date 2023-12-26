package com.fa.junittesting.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room
import com.fa.junittesting.data.local.AppDatabase
import com.fa.junittesting.data.local.ProductDao
import com.fa.junittesting.data.remote.PixabayAPI
import com.fa.junittesting.other.Constants.BASE_URL
import com.fa.junittesting.other.Constants.DATABASE_NAME
import com.fa.junittesting.repositories.DefaultProductRepository
import com.fa.junittesting.repositories.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideProductDatabase(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDefaultProductRepository(
        dao:ProductDao,
        api: PixabayAPI
    )= DefaultProductRepository(dao, api) as ProductRepository

    @Singleton
    @Provides
    fun provideProductDao(
        database: AppDatabase
    ) = database.ProductDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }

}