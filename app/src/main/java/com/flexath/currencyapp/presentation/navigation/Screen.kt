package com.flexath.currencyapp.presentation.navigation

import com.flexath.currencyapp.presentation.constants.CurrencyConvertArg
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home: Screen()

    @Serializable
    data class Detail(
        val currencyArg: CurrencyConvertArg
    ): Screen()
}