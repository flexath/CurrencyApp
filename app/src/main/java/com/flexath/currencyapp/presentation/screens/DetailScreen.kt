package com.flexath.currencyapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flexath.currencyapp.presentation.navigation.Screen
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.CurrencyTypography
import com.flexath.currencyapp.ui.theme.Dimensions

fun NavGraphBuilder.detailScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    onNavigate: () -> Unit
) {
    composable<Screen.Detail> {
        DetailScreen(
            modifier = modifier,
            dimens = dimens,
            colorScheme = colorScheme,
            typography = typography,
            onNavigate = onNavigate
        )
    }
}

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    onNavigate: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Detail Screen",
            modifier = Modifier.clickable {
                onNavigate()
            }
        )
    }
}