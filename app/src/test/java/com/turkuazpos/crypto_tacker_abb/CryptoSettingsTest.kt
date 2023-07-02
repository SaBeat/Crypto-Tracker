package com.turkuazpos.crypto_tacker_abb

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import android.content.SharedPreferences
import com.turkuazpos.crypto_tacker_abb.data.local.CryptoSettings
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CryptoSettingsTests {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    lateinit var sharedPreferences: SharedPreferences
    lateinit var context: Context
    lateinit var cryptoSettings: CryptoSettings

    @Before
    fun before() {
        context = Mockito.mock(Context::class.java)
        sharedPreferences = Mockito.mock(SharedPreferences::class.java)
        Mockito.`when`(context.getSharedPreferences(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyInt()
        ))
            .thenReturn(sharedPreferences)
        Mockito.`when`(sharedPreferences.getString(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        ))
            .thenReturn("15.0=25.0")

        cryptoSettings = CryptoSettings(context)
    }

    @Test
    fun testCryptoSettings_readMinMaxPair() {
        val pair =
            cryptoSettings.stringToMinMaxPair(sharedPreferences.getString(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
            ))
        Assert.assertEquals(Pair(15.0, 25.0), pair)
    }

}