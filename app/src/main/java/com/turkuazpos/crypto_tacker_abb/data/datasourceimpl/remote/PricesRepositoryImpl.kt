package com.turkuazpos.crypto_tacker_abb.data.datasourceimpl.remote

import com.turkuazpos.crypto_tacker_abb.data.datasource.remote.PricesRepository
import com.turkuazpos.crypto_tacker_abb.data.remote.ApiServices
import javax.inject.Inject

class PricesRepositoryImpl @Inject constructor(private val apiServices: ApiServices) :
    PricesRepository {

    override suspend fun getPrices(ids: String, currency: String) =
        apiServices.getPrice(ids, currency).entities
}