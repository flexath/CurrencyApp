package com.flexath.currencyapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.flexath.currencyapp.R
import com.flexath.currencyapp.presentation.navigation.Screen
import com.flexath.currencyapp.presentation.screens.components.CustomExposedDropdown
import com.flexath.currencyapp.presentation.screens.components.CustomFilledButton
import com.flexath.currencyapp.presentation.screens.components.SearchBasicTextField
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
    onNavigate: () -> Unit
) {
    composable<Screen.Home> {
        HomeScreen(
            modifier = modifier,
            dimens = dimens,
            colorScheme = colorScheme,
            typography = typography,
            onNavigate = onNavigate
        )
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    onNavigate: () -> Unit = {},
) {
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
                CustomExposedDropdown(
                    modifier = Modifier.weight(1f),
                    dimens = dimens,
                    colorScheme = colorScheme,
                    options = listOf("USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY","USD", "EUR", "JPY",),
                    label = "From",
                    expanded = currencyFromExpanded,
                    onExpanded = { isExpanded ->
                        currencyFromExpanded = isExpanded
                    },
                    selectedValue = currentFromSelectedType,
                    onValueChangedEvent = { selectedType ->
                        currentFromSelectedType = selectedType
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

                CustomExposedDropdown(
                    modifier = Modifier.weight(1f),
                    dimens = dimens,
                    colorScheme = colorScheme,
                    options = listOf("USD", "EUR", "JPY", "THB", "SGD"),
                    label = "To",
                    expanded = currencyToExpanded,
                    onExpanded = { isExpanded ->
                        currencyToExpanded = isExpanded
                    },
                    selectedValue = currentToSelectedType,
                    onValueChangedEvent = { selectedType ->
                        currentToSelectedType = selectedType
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
                items(count = 10) {

                    if(it == 0) {
                        Spacer(modifier = Modifier.width(dimens.mediumPadding3))
                    } else {
                        Spacer(modifier = Modifier.width(dimens.smallPadding2))
                    }

                    FilterChip(
                        selected = true,
                        shape = RoundedCornerShape(dimens.smallPadding4),
                        label = {
                            Text(
                                text = "USD",
                                style = typography.labelMedium,
                                color = colorScheme.colorPrimary
                            )
                        },
                        onClick = {

                        }
                    )

                    if(it == 9) {
                        Spacer(modifier = Modifier.width(dimens.mediumPadding3))
                    } else {
                        Spacer(modifier = Modifier.width(dimens.smallPadding2))
                    }
                }
            }
        }
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
        )
    }
}