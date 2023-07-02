package com.turkuazpos.crypto_tacker_abb.data.remote.model

import com.turkuazpos.crypto_tacker_abb.data.local.model.PriceEntity


data class PricesDto(
    val bitcoin: Price?,
    val ethereum: Price?,
    val ripple: Price?,
) {
    val entities: List<PriceEntity>
        get() = listOf(
            PriceEntity("Bitcoin", "bitcoind", bitcoin?.usd),
            PriceEntity("Ethereum", "ethereum", ethereum?.usd),
            PriceEntity("Ripple", "ripple", ripple?.usd),
        )
}