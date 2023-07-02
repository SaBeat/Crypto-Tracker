package com.turkuazpos.crypto_tacker_abb.data.datasource.local

interface RecordsRepository {

    fun saveNewRecord(`for`: String, value: Double)

    fun readRecords(`for`: String): List<Double>

}