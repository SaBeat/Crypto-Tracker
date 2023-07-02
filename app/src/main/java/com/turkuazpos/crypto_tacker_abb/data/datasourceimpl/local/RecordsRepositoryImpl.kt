package com.turkuazpos.crypto_tacker_abb.data.datasourceimpl.local

import com.turkuazpos.crypto_tacker_abb.data.datasource.local.RecordsRepository
import com.turkuazpos.crypto_tacker_abb.data.local.CryptoHistory
import javax.inject.Inject

class RecordsRepositoryImpl @Inject constructor(private val cryptoHistory: CryptoHistory) :
    RecordsRepository {

    override fun saveNewRecord(`for`: String, value: Double) {
        cryptoHistory.addRecord(`for`, value)
    }

    override fun readRecords(`for`: String) = cryptoHistory.readRecords(`for`)
}