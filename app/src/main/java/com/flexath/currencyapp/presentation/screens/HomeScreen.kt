package com.flexath.currencyapp.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.flexath.currencyapp.R
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import com.flexath.currencyapp.presentation.constants.CurrencyConvertArg
import com.flexath.currencyapp.presentation.constants.popularCurrencyList
import com.flexath.currencyapp.presentation.navigation.Screen
import com.flexath.currencyapp.presentation.screens.components.AppSearchField
import com.flexath.currencyapp.presentation.screens.components.CurrencyCardShimmerEffect
import com.flexath.currencyapp.presentation.screens.components.CustomFilledButton
import com.flexath.currencyapp.presentation.screens.components.EditableCustomOutlinedTextField
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
import kotlinx.coroutines.delay

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    navController: NavHostController,
    currencyViewModel: CurrencyViewModel
) {
    composable<Screen.Home> {

        var selectedPopularCurrency by rememberSaveable {
            mutableStateOf(popularCurrencyList[0])
        }

        var currentSelectedChipIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        val currentFromState = currencyViewModel.currencyFromStateFlow.collectAsStateWithLifecycle()
        val currentToState = currencyViewModel.currencyToStateflow.collectAsStateWithLifecycle()
        val amountState = currencyViewModel.amountStateflow.collectAsStateWithLifecycle()
        val isCallRealTimeRates = currencyViewModel.isCallRealTimeRates.collectAsStateWithLifecycle()

        var isFirstLaunch by rememberSaveable {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = selectedPopularCurrency) {
            if(!isCallRealTimeRates.value) {
                if(!isFirstLaunch) {
                    delay(2000)
                }
                isFirstLaunch = true
                currencyViewModel.getRealTimeRates(
                    source = selectedPopularCurrency
                )
                currencyViewModel.updateIsCallRealTimeRates(true)
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
            amount = amountState.value,
            currentFromSelectedType = currentFromState.value,
            currentToSelectedType = currentToState.value,
            supportedCurrenciesState = supportedCurrenciesState,
            currentSelectedChipIndex = currentSelectedChipIndex,
            onSelectedChipIndex = {
                currentSelectedChipIndex = it
            },
            onClickCurrencyTextField = {
                // that api call is not that necessary , cause I want to show loading and error states ( hee )
                currencyViewModel.getSupportedCurrencies()
            },
            onSelectedPopularCurrency = {
                currencyViewModel.updateIsCallRealTimeRates(false)
                selectedPopularCurrency = it
            },
            onQueryChange = {
                currencyViewModel.updateAmount(it)
            },
            onSelectCurrencyFrom = {
                currencyViewModel.updateCurrencyFrom(it)
            },
            onSelectCurrencyTo = {
                currencyViewModel.updateCurrencyTo(it)
            },
            onNavigate = {
                navController.navigate(
                    Screen.Detail(
                        currencyArg = CurrencyConvertArg(
                            fromCurrency = currentFromState.value,
                            toCurrency = currentToState.value,
                            amount = amountState.value
                        )
                    )
                )
            }
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
    amount: String = "",
    currentFromSelectedType: String = "",
    currentToSelectedType: String = "",
    onClickCurrencyTextField: () -> Unit = {},
    onSelectedPopularCurrency: (String) -> Unit = {},
    onQueryChange: (String) -> Unit = {},
    onSelectCurrencyFrom: (String) -> Unit = {},
    onSelectCurrencyTo: (String) -> Unit = {},
    onNavigate: () -> Unit = {},
    currentSelectedChipIndex: Int,
    onSelectedChipIndex: (Int) -> Unit,
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
            onSelectCurrencyFrom(it)
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
            onSelectCurrencyTo(it)
        }
    )

    LazyColumn(
        modifier = modifier
    ) {
        item {
            Spacer(modifier = Modifier.height(dimens.largePadding10))

            EditableCustomOutlinedTextField(
                query = amount,
                onQueryChange = {
                    onQueryChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.mediumPadding3),
                hint = stringResource(R.string.lbl_enter_currency),
                dimens = dimens
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
                            painter = if (currencyFromExpanded) {
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
                            painter = if (currencyToExpanded) {
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
                items(count = minOf(popularCurrencyList.size, 10)) { index ->
                    val currency = popularCurrencyList[index]

                    if (index == 0) {
                        Spacer(modifier = Modifier.width(dimens.mediumPadding3))
                    } else {
                        Spacer(modifier = Modifier.width(dimens.smallPadding2))
                    }

                    FilterChip(
                        selected = currentSelectedChipIndex == index,
                        shape = RoundedCornerShape(dimens.smallPadding4),
                        label = {
                            Text(
                                text = currency,
                                style = typography.labelMedium,
                                color = colorScheme.colorPrimary
                            )
                        },
                        onClick = {
                            onSelectedChipIndex(index)
                            onSelectedPopularCurrency(currency)
                        }
                    )

                    if (index == 9) {
                        Spacer(modifier = Modifier.width(dimens.mediumPadding3))
                    } else {
                        Spacer(modifier = Modifier.width(dimens.smallPadding2))
                    }
                }
            }

            Spacer(modifier = Modifier.height(dimens.smallPadding4))

            if (realTimeRatesState.isLoading) {

                CurrencyCardShimmerEffect(
                    dimens = dimens,
                    modifier = Modifier.padding(horizontal = dimens.mediumPadding3).fillMaxWidth()
                )
            } else if (realTimeRatesState.isError) {

                Spacer(modifier = Modifier.height(dimens.largePadding5))

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_empty_state),
                        contentDescription = null,
                        modifier = Modifier.size(dimens.productCardWidth)
                    )
                }
            }
        }

        realTimeRateCardList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimens.mediumPadding3),
            dimens = dimens,
            colorScheme = colorScheme,
            typography = typography,
            currencyList = realTimeRatesState.data
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    CurrencyAppTheme {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            dimens = MaterialTheme.currencyDimens,
            colorScheme = MaterialTheme.currencyColorScheme,
            typography = MaterialTheme.currencyTypography,
            realTimeRatesState = ViewModelUiState(),
            supportedCurrenciesState = ViewModelUiState(),
            amount = "",
            currentSelectedChipIndex = 0,
            onSelectedChipIndex = {

            }
        )
    }
}