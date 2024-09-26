package com.flexath.currencyapp.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flexath.currencyapp.R
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import com.flexath.currencyapp.presentation.navigation.Screen
import com.flexath.currencyapp.presentation.screens.components.AppSearchField
import com.flexath.currencyapp.presentation.screens.components.CustomFilledButton
import com.flexath.currencyapp.presentation.screens.components.SearchBasicTextField
import com.flexath.currencyapp.presentation.screens.components.SearchFilterBottomSheet
import com.flexath.currencyapp.presentation.screens.components.realTimeRateCardList
import com.flexath.currencyapp.presentation.states.ViewModelUiState
import com.flexath.currencyapp.presentation.viewmodels.CurrencyViewModel
import com.flexath.currencyapp.ui.theme.CurrencyAppTheme
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.CurrencyTypography
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import com.flexath.currencyapp.ui.theme.currencyDimens
import com.flexath.currencyapp.ui.theme.currencyTypography

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    onNavigate: () -> Unit,
    currencyViewModel: CurrencyViewModel
) {
    composable<Screen.Home> {

        val isCallRealTimeRates = currencyViewModel.isCallRealTimeRates.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = isCallRealTimeRates.value) {
            if(!isCallRealTimeRates.value) {
//                currencyViewModel.getRealTimeRates()
            }
        }

        val realTimeRatesState by currencyViewModel.realTimeRates.collectAsStateWithLifecycle()
        val supportedCurrenciesState by currencyViewModel.supportedCurrencies.collectAsStateWithLifecycle()

        HomeScreen(
            modifier = modifier,
            dimens = dimens,
            colorScheme = colorScheme,
            typography = typography,
            realTimeRatesState = realTimeRatesState,
            supportedCurrenciesState = supportedCurrenciesState,
            onClickCurrencyTextField = {
                // that api call is not that necessary , cause I want to show loading and error states ( hee )
                currencyViewModel.getSupportedCurrencies()
            },
            onNavigate = onNavigate
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    realTimeRatesState: ViewModelUiState<List<CurrencyVO>>,
    supportedCurrenciesState: ViewModelUiState<List<SupportedCurrencyVO>>,
    onClickCurrencyTextField: () -> Unit = {},
    onNavigate: () -> Unit = {},
) {
    val currencyFromBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val currencyToBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var currencyFromExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var currencyToExpanded by rememberSaveable {
        mutableStateOf(false)
    }

    var currentFromSelectedType by rememberSaveable {
        mutableStateOf("")
    }

    var currentToSelectedType by rememberSaveable {
        mutableStateOf("")
    }

    var currentSelectedChipIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    SearchFilterBottomSheet(
        modifier = modifier,
        dimens = dimens,
        colorScheme = colorScheme,
        typography = typography,
        title = "From Currency:",
        supportedCurrenciesState = supportedCurrenciesState,
        sheetState = currencyFromBottomSheetState,
        bottomSheetShow = currencyFromExpanded,
        onSheetShowChange = {
            currencyFromExpanded = it
        },
        onSelected = {
            currentFromSelectedType = it
        }
    )

    SearchFilterBottomSheet(
        modifier = modifier,
        dimens = dimens,
        colorScheme = colorScheme,
        typography = typography,
        title = "To Currency:",
        supportedCurrenciesState = supportedCurrenciesState,
        sheetState = currencyToBottomSheetState,
        bottomSheetShow = currencyToExpanded,
        onSheetShowChange = {
            currencyToExpanded = it
        },
        onSelected = {
            currentToSelectedType = it
        }
    )

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Spacer(modifier = Modifier.height(dimens.largePadding10))

            SearchBasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.mediumPadding3),
                textFieldState = rememberTextFieldState(),
                hint = stringResource(R.string.lbl_enter_currency)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.mediumPadding3)
                    .padding(top = dimens.mediumPadding3)
            ) {
                AppSearchField(
                    modifier = Modifier.weight(1f),
                    query = currentFromSelectedType,
                    onValueChange = {},
                    placeHolder = "From",
                    enabled = false,
                    readOnly = true,
                    background = MaterialTheme.currencyColorScheme.colorSearchField,
                    iconTint = MaterialTheme.colorScheme.primary,
                    trailingIcon = {
                        Icon(
                            painter = if(currencyFromExpanded) {
                                painterResource(id = R.drawable.ic_chevron_up)
                            } else {
                                painterResource(id = R.drawable.ic_chevron_down_20dp)
                            },
                            contentDescription = null,
                            tint = MaterialTheme.currencyColorScheme.colorIcon,
                            modifier = Modifier
                                .padding(end = dimens.mediumPadding3, start = dimens.smallPadding2)
                                .size(dimens.mediumPadding5)
                        )
                    },
                    onClick = {
                        onClickCurrencyTextField()
                        currencyFromExpanded = !currencyFromExpanded
                    }
                )

                Icon(
                    painter = painterResource(R.drawable.ic_convert),
                    contentDescription = null,
                    tint = MaterialTheme.currencyColorScheme.colorIcon,
                    modifier = Modifier
                        .padding(horizontal = dimens.mediumPadding3)
                        .align(Alignment.CenterVertically)
                )

                AppSearchField(
                    modifier = Modifier.weight(1f),
                    query = currentToSelectedType,
                    onValueChange = {},
                    placeHolder = "To",
                    enabled = false,
                    readOnly = true,
                    background = MaterialTheme.currencyColorScheme.colorSearchField,
                    iconTint = MaterialTheme.colorScheme.primary,
                    trailingIcon = {
                        Icon(
                            painter = if(currencyToExpanded) {
                                painterResource(id = R.drawable.ic_chevron_up)
                            } else {
                                painterResource(id = R.drawable.ic_chevron_down_20dp)
                            },
                            contentDescription = null,
                            tint = MaterialTheme.currencyColorScheme.colorIcon,
                            modifier = Modifier
                                .padding(end = dimens.mediumPadding3, start = dimens.smallPadding2)
                                .size(dimens.mediumPadding5)
                        )
                    },
                    onClick = {
                        onClickCurrencyTextField()
                        currencyToExpanded = !currencyFromExpanded
                    }
                )
            }

            CustomFilledButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.mediumPadding3)
                    .padding(top = dimens.mediumPadding3),
                dimens = dimens,
                buttonText = stringResource(R.string.lbl_convert),
                isLoading = false,
                onClick = {
                    onNavigate()
                }
            )

            Spacer(modifier = Modifier.height(dimens.mediumPadding5))

            LazyRow {
                items(count = minOf(supportedCurrenciesState.data?.size ?: 0, 10)) {index ->
                    val currency = supportedCurrenciesState.data?.get(index)

                    if(index == 0) {
                        Spacer(modifier = Modifier.width(dimens.mediumPadding3))
                    } else {
                        Spacer(modifier = Modifier.width(dimens.smallPadding2))
                    }

                    currency?.let {
                        FilterChip(
                            selected = currentSelectedChipIndex == index,
                            shape = RoundedCornerShape(dimens.smallPadding4),
                            label = {
                                Text(
                                    text = currency.currencyCode,
                                    style = typography.labelMedium,
                                    color = colorScheme.colorPrimary
                                )
                            },
                            onClick = {
                                currentSelectedChipIndex = index
                            }
                        )
                    }

                    if(index == 9) {
                        Spacer(modifier = Modifier.width(dimens.mediumPadding3))
                    } else {
                        Spacer(modifier = Modifier.width(dimens.smallPadding2))
                    }
                }
            }

            Spacer(modifier = Modifier.height(dimens.smallPadding4))

            if(realTimeRatesState.isLoading) {
                Spacer(modifier = Modifier.height(dimens.largePadding5))

                CircularProgressIndicator(
                    color = colorScheme.colorPrimary,
                    strokeWidth = dimens.smallPadding1
                )
            } else if(realTimeRatesState.isError) {

                Spacer(modifier = Modifier.height(dimens.largePadding5))

                Image(
                    painter = painterResource(R.drawable.img_empty_state),
                    contentDescription = null,
                    modifier = Modifier.size(dimens.productCardWidth)
                )
            }
        }

        realTimeRateCardList(
            modifier = Modifier.fillMaxWidth().padding(horizontal = dimens.mediumPadding3),
            dimens = dimens,
            colorScheme = colorScheme,
            typography = typography,
            currencyList = realTimeRatesState.data.orEmpty()
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CurrencyAppTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            dimens = MaterialTheme.currencyDimens,
            colorScheme = MaterialTheme.currencyColorScheme,
            typography = MaterialTheme.currencyTypography,
            supportedCurrenciesState = ViewModelUiState(),
            realTimeRatesState = ViewModelUiState()
        )
    }
}