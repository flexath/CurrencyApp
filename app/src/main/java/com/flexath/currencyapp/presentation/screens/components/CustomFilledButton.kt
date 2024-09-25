package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import com.flexath.currencyapp.ui.theme.currencyTypography

@Composable
fun CustomFilledButton(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    buttonText: String,
    onClick: () -> Unit,
    isLoading: Boolean = false
) {
    Button(
        modifier = modifier.heightIn(min = dimens.extraLargePadding5),
        shape = RoundedCornerShape(dimens.mediumPadding1),
        enabled = !isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.currencyColorScheme.colorPrimary,
            contentColor = MaterialTheme.currencyColorScheme.colorTitleText
        ),
        onClick = {
            onClick()
        }
    ) {
        if(isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.currencyColorScheme.colorBackground,
                strokeWidth = dimens.smallPadding1,
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
        } else {
            Text(
                text = buttonText,
                style = MaterialTheme.currencyTypography.bodyLarge,
                color = MaterialTheme.currencyColorScheme.colorBackground,
            )
        }
    }
}