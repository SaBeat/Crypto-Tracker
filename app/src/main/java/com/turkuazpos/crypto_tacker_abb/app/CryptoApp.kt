package com.turkuazpos.crypto_tacker_abb.app

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class CryptoApp : Application(), Configuration.Provider{
    companion object {
        const val BACKGROUND_CHECK_WORK = "BACKGROUND_CHECK_WORK"
    }

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()

        //For test
        getSharedPreferences("crypto_settings", Context.MODE_PRIVATE).edit().putString(
            "Bitcoin", "15.0=25.0"
        ).commit()

        val request = PeriodicWorkRequestBuilder<BackgroundWorker>(15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            BACKGROUND_CHECK_WORK,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}