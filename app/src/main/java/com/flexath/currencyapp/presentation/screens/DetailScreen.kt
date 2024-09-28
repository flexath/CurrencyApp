package com.flexath.currencyapp.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.flexath.currencyapp.R
import com.flexath.currencyapp.domain.model.CurrencyConverterVO
import com.flexath.currencyapp.presentation.constants.CurrencyConvertArg
import com.flexath.currencyapp.presentation.navigation.Screen
import com.flexath.currencyapp.presentation.states.ViewModelUiState
import com.flexath.currencyapp.presentation.utils.CustomNavType
import com.flexath.currencyapp.presentation.viewmodels.CurrencyViewModel
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.CurrencyTypography
import com.flexath.currencyapp.ui.theme.CustomFont
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import com.flexath.currencyapp.ui.theme.currencyDimens
import com.flexath.currencyapp.ui.theme.currencyTypography
import com.flexath.currencyapp.ui.theme.getFont
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.reflect.typeOf

fun NavGraphBuilder.detailScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    currencyViewModel: CurrencyViewModel,
    navController: NavHostController
) {
    composable<Screen.Detail>(
        typeMap = mapOf(typeOf<CurrencyConvertArg>() to CustomNavType(
            clazz = CurrencyConvertArg::class,
            serializer = CurrencyConvertArg.serializer()
        ))
    ) {
        val convertCurrencyArg = it.toRoute<Screen.Detail>()

        LaunchedEffect(key1 = convertCurrencyArg.currencyArg) {
            currencyViewModel.convertCurrency(
                from = convertCurrencyArg.currencyArg.fromCurrency,
                to = convertCurrencyArg.currencyArg.toCurrency,
                amount = convertCurrencyArg.currencyArg.amount
            )
        }

        val convertedCurrencyState = currencyViewModel.convertCurrency.collectAsStateWithLifecycle()

        DetailScreen(
            modifier = modifier,
            dimens = dimens,
            colorScheme = colorScheme,
            typography = typography,
            onNavigate = {
                navController.navigateUp()
            },
            convertedCurrencyState = convertedCurrencyState.value
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    onNavigate: () -> Unit = {},
    convertedCurrencyState: ViewModelUiState<CurrencyConverterVO>,
) {
    val currencyFrom = convertedCurrencyState.data?.query?.from
    val currencyTo = convertedCurrencyState.data?.query?.to
    val amountPerCurrency = BigDecimal(convertedCurrencyState.data?.info?.quote ?: 0.0).setScale(4, RoundingMode.DOWN).toDouble()
    val amount = convertedCurrencyState.data?.result.toString()

    Scaffold(
        modifier = modifier.background(color = colorScheme.colorBackground),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Details",
                        style = typography.titleMedium.copy(
                            fontFamily = getFont(CustomFont.Poppins)
                        ),
                        color = colorScheme.colorTitleText
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorScheme.colorBackground,
                    titleContentColor = colorScheme.colorTitleText,
                    navigationIconContentColor = colorScheme.colorIcon
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigate()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            tint = colorScheme.colorTitleText
                        )
                    }
                }
            )
        }
    ) {
        val topPadding = it.calculateTopPadding()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding),
        ) {

            if(convertedCurrencyState.isLoading) {

            } else if(convertedCurrencyState.isError) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No data found",
                        style = typography.titleSmall,
                        color = colorScheme.colorTitleText
                    )
                }
            }

            AnimatedVisibility(
                visible = convertedCurrencyState.data != null,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.currency),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                    if(currencyFrom == null && currencyTo == null) {
                        Text(
                            text = stringResource(R.string.lbl_there_is_no_data),
                            style = typography.titleSmall.copy(
                                fontFamily = getFont(CustomFont.Poppins)
                            ),
                            color = colorScheme.colorHint
                        )

                        Spacer(modifier = Modifier.height(dimens.mediumPadding3))
                    }

                    if(currencyFrom != null && currencyTo != null) {
                        Text(
                            text = "1 $currencyFrom equals $amountPerCurrency $currencyTo",
                            style = typography.titleSmall.copy(
                                fontFamily = getFont(CustomFont.Poppins)
                            ),
                            color = colorScheme.colorHint
                        )
                    }

                    Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                    if(currencyFrom != null && currencyTo != null) {
                        Text(
                            text = "$amount $currencyTo",
                            fontSize = 48.sp,               // shouldn't hardcoded
                            fontFamily = getFont(CustomFont.Poppins),
                            fontWeight = FontWeight.SemiBold,
                            color = colorScheme.colorTitleText
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        dimens = MaterialTheme.currencyDimens,
        colorScheme = MaterialTheme.currencyColorScheme,
        typography = MaterialTheme.currencyTypography,
        convertedCurrencyState = ViewModelUiState(),
        onNavigate = {}
    )
}