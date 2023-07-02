package com.turkuazpos.crypto_tacker_abb.data.datasource.remote

import com.turkuazpos.crypto_tacker_abb.data.local.model.PriceEntity

interface PricesRepository {

    suspend fun getPrices(ids: String, currency: String): List<PriceEntity>

}