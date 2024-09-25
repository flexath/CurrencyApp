package com.flexath.currencyapp.domain.model

data class SupportedCurrencyEntity(
    val success: Boolean?,
    val terms: String?,
    val privacy: String?,
    val currencies: List<SupportedCurrencyVO>?
)
