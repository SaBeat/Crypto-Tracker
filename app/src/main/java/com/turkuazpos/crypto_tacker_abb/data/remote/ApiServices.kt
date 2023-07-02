package com.turkuazpos.crypto_tacker_abb.data.remote

import com.turkuazpos.crypto_tacker_abb.data.remote.model.PricesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("simple/price")
    suspend fun getPrice(
        @Query("ids") ids: String,
        @Query("vs_currencies") currency: String
    ): PricesDto

}