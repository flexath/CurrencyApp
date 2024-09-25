package com.flexath.currencyapp.domain.usecase

import com.flexath.currencyapp.data.repository.CurrencyRepository
import javax.inject.Inject

class GetSupportedCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke() = currencyRepository.getSupportedCurrencies()
}