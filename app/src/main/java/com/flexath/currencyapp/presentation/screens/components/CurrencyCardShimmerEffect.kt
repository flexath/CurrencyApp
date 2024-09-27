package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.currencyapp.presentation.utils.skeletonEffect
import com.flexath.currencyapp.ui.theme.CurrencyAppTheme
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.currencyDimens

@Composable
fun CurrencyCardShimmerEffect(
    dimens: Dimensions,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimens.mediumPadding1),
        modifier = modifier
    ) {

        repeat(20) {
            CurrencyCardShimmerItem(
                dimens = dimens
            )
        }
    }
}

@Composable
fun CurrencyCardShimmerItem(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
) {
    Row(
        modifier = modifier.clickable {},
    ) {
        Box(
            modifier = Modifier.size(dimens.largePadding2).clip(CircleShape).skeletonEffect()
        )

        Spacer(modifier = Modifier.width(dimens.mediumPadding3))

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(dimens.mediumPadding3)
                        .skeletonEffect()
                )

                Spacer(modifier = Modifier.width(dimens.mediumPadding3))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .height(dimens.mediumPadding3)
                        .skeletonEffect()
                )
            }

            Spacer(modifier = Modifier.heightIn(dimens.smallPadding4))

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .height(dimens.mediumPadding1)
                    .skeletonEffect()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CurrencyCardShimmerEffectPreview() {
    CurrencyAppTheme {
        CurrencyCardShimmerEffect(
            dimens = MaterialTheme.currencyDimens,
        )
    }
}