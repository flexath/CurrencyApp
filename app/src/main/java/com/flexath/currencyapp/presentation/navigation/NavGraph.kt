package com.flexath.currencyapp.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.flexath.currencyapp.presentation.screens.detailScreen
import com.flexath.currencyapp.presentation.screens.homeScreen
import com.flexath.currencyapp.presentation.viewmodels.CurrencyViewModel
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import com.flexath.currencyapp.ui.theme.currencyDimens
import com.flexath.currencyapp.ui.theme.currencyTypography

@Composable
fun SetUpNavGraph(
    modifier: Modifier = Modifier,
    currencyViewModel: CurrencyViewModel,
    navController: NavHostController
) {
    val dimens = MaterialTheme.currencyDimens
    val colorScheme = MaterialTheme.currencyColorScheme
    val typography = MaterialTheme.currencyTypography

    Scaffold(
        modifier = modifier,
        containerColor = colorScheme.colorBackground,
    ) {
        val bottomPadding = it.calculateBottomPadding()
        val topPadding = it.calculateTopPadding()

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Screen.Home
        ) {
            homeScreen(
                modifier = Modifier.fillMaxSize().statusBarsPadding(),
                dimens = dimens,
                colorScheme = colorScheme,
                typography = typography,
                currencyViewModel = currencyViewModel,
                navController = navController
            )

            detailScreen(
                modifier = Modifier.fillMaxSize(),
                dimens = dimens,
                colorScheme = colorScheme,
                typography = typography,
                currencyViewModel = currencyViewModel,
                navController = navController
            )
        }
    }
}