package com.example.hellomvvm5.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class CryptocurrenciesViewModelTest {

    private lateinit var cryptocurrenciesViewModel: CryptocurrenciesViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        cryptocurrenciesViewModel = CryptocurrenciesViewModel()
    }

    @Test
    fun testInitial() {
        val liveData = cryptocurrenciesViewModel.getCryptocurrencies()
        assertEquals(liveData.value!![0]!!.name, "BITCOIN")
    }

    @Test
    fun testAddCryptocurrency() {
        cryptocurrenciesViewModel.addToCryptocurrencies("Monero", "monero.org")
        val liveData = cryptocurrenciesViewModel.getCryptocurrencies()
        assertEquals(liveData.value!![4]!!.name, "MONERO")
    }
}