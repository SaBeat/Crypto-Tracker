package com.turkuazpos.crypto_tacker_abb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turkuazpos.crypto_tacker_abb.data.local.model.PriceEntity
import com.turkuazpos.crypto_tacker_abb.data.datasource.remote.PricesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val pricesRepository: PricesRepository) :
    ViewModel() {

    companion object {
        const val CURRENCY_DOLLARS = "usd"
        const val CRYPTOS = "bitcoin,ethereum,ripple"
    }

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _coins = MutableStateFlow<List<PriceEntity>>(emptyList())
    val coins: StateFlow<List<PriceEntity>> = _coins

    fun getPrices(ids: String = CRYPTOS, currency: String = CURRENCY_DOLLARS) {

        viewModelScope.launch {
            val prices = pricesRepository.getPrices(ids, currency)
            _coins.value = prices
            _loading.value = false
        }
    }

}