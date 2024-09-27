package com.flexath.currencyapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.flexath.currencyapp.presentation.constants.CurrencyConvertArg
import com.flexath.currencyapp.presentation.navigation.Screen
import com.flexath.currencyapp.presentation.utils.CustomNavType
import com.flexath.currencyapp.presentation.viewmodels.CurrencyViewModel
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.CurrencyTypography
import com.flexath.currencyapp.ui.theme.Dimensions
import kotlin.reflect.typeOf

fun NavGraphBuilder.detailScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    onNavigate: () -> Unit,
    currencyViewModel: CurrencyViewModel
) {
    composable<Screen.Detail>(
        typeMap = mapOf(typeOf<CurrencyConvertArg>() to CustomNavType(
            clazz = CurrencyConvertArg::class,
            serializer = CurrencyConvertArg.serializer()
        ))
    ) {
        val convertCurrencyArg = it.toRoute<Screen.Detail>()

        Log.i("DetailScreen", "detailScreen: $convertCurrencyArg")

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