package com.flexath.currencyapp.domain.usecase

import com.flexath.currencyapp.data.repository.CurrencyRepository
import javax.inject.Inject

class GetRealTimeRatesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(
        currencies: String? = null,
        source: String? = null
    ) = currencyRepository.getRealTimeRates(
        currencies = currencies,
        source = source
    )
}