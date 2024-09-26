package com.flexath.currencyapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supported_currency_table")
data class SupportedCurrencyVO(
    @PrimaryKey
    val currencyCode: String,
    val value: String
)