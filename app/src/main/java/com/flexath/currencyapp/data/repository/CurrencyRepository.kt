package com.flexath.currencyapp.data.repository

import androidx.paging.PagingData
import com.flexath.currencyapp.data.remote.utils.SpecificUiState
import com.flexath.currencyapp.domain.model.CurrencyConverterVO
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getRealTimeRates(
        currencies: String? = null,
        source: String? = null
    ): Flow<SpecificUiState<List<CurrencyVO>>>

    fun getSupportedCurrencies(): Flow<SpecificUiState<List<SupportedCurrencyVO>>>

    fun convertCurrency(
        from: String,
        to: String,
        amount: Double
    ): Flow<SpecificUiState<CurrencyConverterVO>>
}