package com.flexath.currencyapp.domain.usecase

import javax.inject.Inject

data class CurrencyUseCases @Inject constructor(
    val getRealTimeRatesUseCase: GetRealTimeRatesUseCase,
    val getSupportedCurrenciesUseCase: GetSupportedCurrenciesUseCase,
    val convertCurrencyUseCase: ConvertCurrencyUseCase
)
