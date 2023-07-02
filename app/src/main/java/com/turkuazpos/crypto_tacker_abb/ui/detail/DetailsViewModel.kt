package com.turkuazpos.crypto_tacker_abb.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turkuazpos.crypto_tacker_abb.data.datasource.local.RecordsRepository
import com.turkuazpos.crypto_tacker_abb.data.datasource.local.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val readSettingsRepository: SettingsRepository,
    private val saveCryptoSettingsRepository: SettingsRepository,
    private val recordsRepository: RecordsRepository
) : ViewModel() {

    private val _minMax = MutableStateFlow(Pair(0.0, 0.0))
    val minMax: StateFlow<Pair<Double, Double>> = _minMax

    private val _records = MutableStateFlow<List<Double>>(emptyList())
    val records: StateFlow<List<Double>> = _records

    fun loadSettings(`for`: String) {

        viewModelScope.launch {
            val loadSettings = readSettingsRepository.loadSettings(`for`)
            _minMax.value = loadSettings
        }
    }

    fun saveSettings(`for`: String, min: String, max: String) {

        viewModelScope.launch {
            saveCryptoSettingsRepository.saveSettings(
                `for`,
                min.toDouble(),
                max.toDouble()
            )
            loadSettings(`for`)
        }
    }

    fun loadRecords(`for`: String) {
        viewModelScope.launch {
            val records = recordsRepository.readRecords(`for`)
            _records.value = records
        }
    }
}