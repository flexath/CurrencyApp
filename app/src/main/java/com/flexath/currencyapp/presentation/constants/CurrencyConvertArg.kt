package com.flexath.currencyapp.presentation.constants

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CurrencyConvertArg(
    val fromCurrency: String,
    val toCurrency: String,
    val amount: String
): Parcelable