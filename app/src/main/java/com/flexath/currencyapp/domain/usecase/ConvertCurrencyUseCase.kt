package com.flexath.currencyapp.domain.usecase

import com.flexath.currencyapp.data.repository.CurrencyRepository
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) {
    operator fun invoke(
        from: String,
        to: String,
        amount: Double
    ) = currencyRepository.convertCurrency(
        from = from,
        to = to,
        amount = amount
    )
}