package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.currencyapp.R
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.CurrencyTypography
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import com.flexath.currencyapp.ui.theme.currencyDimens
import com.flexath.currencyapp.ui.theme.currencyTypography

fun LazyListScope.realTimeRateCardList(
    modifier: Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    currencyList: List<CurrencyVO>
) {
    items(count = currencyList.size) {index ->
        val currency = currencyList[index]

        Spacer(modifier = Modifier.height(dimens.smallPadding4))

        RealTimeRateCard(
            modifier = modifier,
            dimens = dimens,
            colorScheme = colorScheme,
            typography = typography,
            currency = currency
        )

        Spacer(modifier = Modifier.height(dimens.smallPadding4))
    }
}

@Composable
fun RealTimeRateCard(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    currency: CurrencyVO
) {
    Row(
        modifier = modifier.clickable {

        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_location),
            tint = colorScheme.colorPrimary,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(dimens.mediumPadding3))

        Text(
            text = currency.currencyCode,
            style = typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = colorScheme.colorTitleText,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(dimens.mediumPadding3))

        Text(
            text = currency.value.toString(),
            style = typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = colorScheme.colorTitleText,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RealTimeRateCardPreview() {
    RealTimeRateCard(
        modifier = Modifier.padding(horizontal = MaterialTheme.currencyDimens.mediumPadding3).fillMaxWidth(),
        dimens = MaterialTheme.currencyDimens,
        colorScheme = MaterialTheme.currencyColorScheme,
        typography = MaterialTheme.currencyTypography,
        currency = CurrencyVO(
            currencyCode = "USD",
            value = 0.3563
        )
    )
}

