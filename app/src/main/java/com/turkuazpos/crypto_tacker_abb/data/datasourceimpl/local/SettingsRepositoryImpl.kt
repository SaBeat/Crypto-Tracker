package com.turkuazpos.crypto_tacker_abb.data.datasourceimpl.local

import com.turkuazpos.crypto_tacker_abb.data.datasource.local.SettingsRepository
import com.turkuazpos.crypto_tacker_abb.data.local.CryptoSettings
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val settings: CryptoSettings) :
    SettingsRepository {

    override fun loadSettings(`for`: String): Pair<Double, Double> = settings.readMinMaxPair(`for`)!!

    override fun saveSettings(`for`: String, min: Double, max: Double) =
        settings.updateMinMaxPair(`for`, min, max)
}