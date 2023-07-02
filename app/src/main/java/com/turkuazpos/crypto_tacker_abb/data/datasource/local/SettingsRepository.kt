package com.turkuazpos.crypto_tacker_abb.data.datasource.local

interface SettingsRepository {

    fun loadSettings(`for`: String): Pair<Double, Double>

    fun saveSettings(`for`: String, min: Double, max: Double)
}