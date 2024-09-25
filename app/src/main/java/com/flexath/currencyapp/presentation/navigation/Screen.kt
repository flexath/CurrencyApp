package com.flexath.currencyapp.presentation.navigation

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home: Screen()

    @Serializable
    data object Detail: Screen()
}