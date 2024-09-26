package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import com.flexath.currencyapp.ui.theme.CurrencyAppTheme
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.CurrencyTypography
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import com.flexath.currencyapp.ui.theme.currencyDimens
import com.flexath.currencyapp.ui.theme.currencyTypography

fun LazyListScope.supportedCurrencyList(
    modifier: Modifier,
    dimensions: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    currencyList: List<SupportedCurrencyVO>,
    onSelected: (String) -> Unit,
    selectedCurrency: String,
) {
    items(
        count = currencyList.size,
    ) {index ->
        val currency = currencyList[index]

        SupportedCurrency(
            modifier = modifier,
            dimens = dimensions,
            colorScheme = colorScheme,
            typography = typography,
            currency = currency,
            isSelected = selectedCurrency == currency.currencyCode,
            onSelected = { selectedCurrency ->
                onSelected(selectedCurrency)
            }
        )
    }
}

@Composable
fun SupportedCurrency(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    currency: SupportedCurrencyVO,
    isSelected: Boolean,
    onSelected: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimens.mediumPadding3),
        modifier = modifier.clickable { onSelected(currency.currencyCode) }
    ) {
        Text(
            text = currency.currencyCode,
            style = typography.labelMedium,
            color = colorScheme.colorTitleText,
            modifier = Modifier.weight(1f)
        )

        RadioButton(
            selected = isSelected,
            onClick = {
                onSelected(currency.currencyCode)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SupportedCurrencyPreview() {
    CurrencyAppTheme {
        SupportedCurrency(
            modifier = Modifier.fillMaxWidth(),
            dimens = MaterialTheme.currencyDimens,
            colorScheme = MaterialTheme.currencyColorScheme,
            typography = MaterialTheme.currencyTypography,
            onSelected = {},
            isSelected = true,
            currency = SupportedCurrencyVO(
                currencyCode = "USD",
                value = "United States Dollar"
            )
        )
    }
}