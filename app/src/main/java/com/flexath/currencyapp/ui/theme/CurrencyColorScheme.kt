package com.flexath.currencyapp.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CurrencyColorScheme(
    val colorPrimary: Color = Color.Unspecified,
    val colorSecondary: Color = Color.Unspecified,
    val colorBackground: Color = Color.Unspecified,
    val colorTitleText: Color = Color.Unspecified,
    val colorBodyText: Color = Color.Unspecified,
    val colorSuccess: Color = Color.Unspecified,
    val colorError: Color = Color.Unspecified,
    val colorRating: Color = Color.Unspecified,
    val colorIcon: Color = Color.Unspecified,
    val colorDivider: Color = Color.Unspecified,
    val colorStroke: Color = Color.Unspecified
)

val CurrencyLightColorScheme = CurrencyColorScheme(
    colorPrimary = LightPrimary,
    colorSecondary = LightSecondary,
    colorBackground = LightBackground,
    colorTitleText = LightTitleText,
    colorBodyText = LightBodyText,
    colorSuccess = LightSuccess,
    colorError = LightError,
    colorRating = LightRating,
    colorIcon = LightIconColor,
    colorDivider = LightDivider,
    colorStroke = LightStroke
)

val CurrencyDarkColorScheme = CurrencyColorScheme(
    colorPrimary = DarkPrimary,
    colorSecondary = DarkSecondary,
    colorBackground = DarkBackground,
    colorTitleText = DarkTitleText,
    colorBodyText = DarkBodyText,
    colorSuccess = DarkSuccess,
    colorError = DarkError,
    colorRating = DarkRating,
    colorIcon = DarkIconColor,
    colorDivider = DarkDivider,
    colorStroke = DarkStroke
)