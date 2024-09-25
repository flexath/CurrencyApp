package com.flexath.currencyapp.domain.model

data class CurrencyConverterVO(
    val success: Boolean?,
    val terms: String?,
    val privacy: String?,
    val query: QueryVO?,
    val info: InfoVO?,
    val result: Double?
)
