package com.turkuazpos.crypto_tacker_abb.di

import com.turkuazpos.crypto_tacker_abb.data.local.CryptoHistory
import com.turkuazpos.crypto_tacker_abb.data.local.CryptoSettings
import com.turkuazpos.crypto_tacker_abb.data.remote.ApiServices
import com.turkuazpos.crypto_tacker_abb.data.datasource.*
import com.turkuazpos.crypto_tacker_abb.data.datasource.local.RecordsRepository
import com.turkuazpos.crypto_tacker_abb.data.datasource.local.SettingsRepository
import com.turkuazpos.crypto_tacker_abb.data.datasource.remote.PricesRepository
import com.turkuazpos.crypto_tacker_abb.data.datasourceimpl.local.RecordsRepositoryImpl
import com.turkuazpos.crypto_tacker_abb.data.datasourceimpl.local.SettingsRepositoryImpl
import com.turkuazpos.crypto_tacker_abb.data.datasourceimpl.remote.PricesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun providePricesRepository(apiServices: ApiServices): PricesRepository =
        PricesRepositoryImpl(apiServices)

    @Provides
    @Singleton
    fun provideSettingsRepository(settings: CryptoSettings): SettingsRepository =
        SettingsRepositoryImpl(settings)

    @Provides
    @Singleton
    fun provideRecordsRepository(history: CryptoHistory): RecordsRepository =
        RecordsRepositoryImpl(history)

}