package com.flexath.currencyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO

@Dao
interface CurrencyDao {
    @Upsert
    suspend fun insertSupportedCurrencies(supportedCurrencies: List<SupportedCurrencyVO>)

    @Query("SELECT * FROM supported_currency_table")
    fun getSupportedCurrencies(): List<SupportedCurrencyVO>
}