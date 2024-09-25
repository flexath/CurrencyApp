package com.flexath.currencyapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ProvideAppThemeUtils(
    appDimens: Dimensions,
    typography: CurrencyTypography,
    colorScheme: CurrencyColorScheme,
    content: @Composable () -> Unit,
) {
    val appDimensions = remember {
        appDimens
    }

    val currencyColorTheme = remember {
        colorScheme
    }

    val currencyTypography = remember { typography }

    CompositionLocalProvider(LocalAppDimens provides appDimensions,LocalCurrencyColorScheme provides currencyColorTheme, LocalCurrencyTypography provides currencyTypography) {
        content()
    }
}

val LocalAppDimens = compositionLocalOf {
    Dimensions()
}

val LocalCurrencyColorScheme = staticCompositionLocalOf {
    CurrencyColorScheme()
}

val LocalCurrencyTypography = staticCompositionLocalOf { CurrencyTypography() }

val ScreenOrientation
    @Composable
    get() = LocalConfiguration.current.orientation