package com.turkuazpos.crypto_tacker_abb.di

import com.turkuazpos.crypto_tacker_abb.data.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val BASE_URL = "https://api.coingecko.com/api/v3/"

//    @Provides
//    @Singleton
//    fun provideOkHttpClient() = OkHttpClient.Builder()
//        .readTimeout(20, TimeUnit.SECONDS)
//        .connectTimeout(20, TimeUnit.SECONDS)
//        .addInterceptor(HttpLoggingInterceptor().apply {
//            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
//        })
//        .build()

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)
}