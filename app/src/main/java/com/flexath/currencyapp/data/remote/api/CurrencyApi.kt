package com.flexath.currencyapp.data.remote.api

import com.flexath.currencyapp.BuildConfig
import com.flexath.currencyapp.data.remote.api.CurrencyApiConstants.QUERY_ACCESS_KEY
import com.flexath.currencyapp.data.remote.api.CurrencyApiConstants.QUERY_AMOUNT
import com.flexath.currencyapp.data.remote.api.CurrencyApiConstants.QUERY_CURRENCIES
import com.flexath.currencyapp.data.remote.api.CurrencyApiConstants.QUERY_FROM
import com.flexath.currencyapp.data.remote.api.CurrencyApiConstants.QUERY_SOURCE
import com.flexath.currencyapp.data.remote.api.CurrencyApiConstants.QUERY_TO
import com.flexath.currencyapp.data.remote.dto.CurrencyConvertResponse
import com.flexath.currencyapp.data.remote.dto.RealTimeRateResponse
import com.flexath.currencyapp.data.remote.dto.SupportedCurrenciesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("live")
    suspend fun getRealTimeRates(
        @Query(QUERY_ACCESS_KEY) accessKey: String = BuildConfig.API_KEY,
        @Query(QUERY_CURRENCIES) currencies: String? = null,
        @Query(QUERY_SOURCE) source: String? = null
    ): RealTimeRateResponse

    @GET("list")
    suspend fun getSupportedCurrencies(
        @Query(QUERY_ACCESS_KEY) accessKey: String = BuildConfig.API_KEY
    ): SupportedCurrenciesResponse

    @GET("convert")
    suspend fun convertCurrency(
        @Query(QUERY_ACCESS_KEY) accessKey: String = BuildConfig.API_KEY,
        @Query(QUERY_FROM) from: String,
        @Query(QUERY_TO) to: String,
        @Query(QUERY_AMOUNT) amount: Double
    ): CurrencyConvertResponse
}