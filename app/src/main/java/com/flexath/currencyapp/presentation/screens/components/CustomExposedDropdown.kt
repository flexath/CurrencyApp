package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.flexath.currencyapp.R
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.CustomFont
import com.flexath.currencyapp.ui.theme.Dimensions
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import com.flexath.currencyapp.ui.theme.currencyTypography
import com.flexath.currencyapp.ui.theme.getFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExposedDropdown(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    options: List<String>,
    label: String,
    expanded: Boolean = false,
    onExpanded: (Boolean) -> Unit,
    selectedValue: String,
    onValueChangedEvent: (String) -> Unit,
) {

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            onExpanded(!expanded)
        },
        modifier = modifier
            .clip(RoundedCornerShape(dimens.smallPadding4))
    ) {
        AppSearchField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            query = selectedValue,
            onValueChange = {},
            placeHolder = label,
            enabled = false,
            readOnly = true,
            background = MaterialTheme.currencyColorScheme.colorSearchField,
            iconTint = MaterialTheme.colorScheme.primary,
            trailingIcon = {
                Icon(
                    painter = if(expanded) {
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

            }
        )

        DropdownMenu(
            modifier = Modifier
                .background(color = colorScheme.colorCardBackground)
                .height(200.dp)
                .exposedDropdownSize(),
            expanded = expanded,
            offset = DpOffset(
                x = 0.dp,
                y = dimens.mediumPadding1
            ),
            onDismissRequest = {
                onExpanded(false)
            }
        ) {
            options.forEach { option: String ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.smallPadding2)
                        .then(
                            if (selectedValue == option) {
                                Modifier.border(
                                    width = dimens.smallPadding0,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(dimens.smallPadding4)
                                )
                            } else {
                                Modifier
                            }
                        ),
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.currencyTypography.bodyMedium.copy(
                                fontFamily = getFont(CustomFont.Poppins)
                            ),
                            color = MaterialTheme.currencyColorScheme.colorBodyText,
                            modifier = Modifier.padding(vertical = dimens.smallPadding5, horizontal = dimens.smallPadding3)
                        )
                    },
                    onClick = {
                        onExpanded(false)
                        onValueChangedEvent(option)
                    }
                )
            }
        }
    }
}