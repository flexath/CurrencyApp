package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.CurrencyTypography
import com.flexath.currencyapp.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFilterBottomSheet(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    typography: CurrencyTypography,
    sheetState: SheetState,
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
) {
    CustomModalBottomSheet(
        modifier = modifier,
        dimens = dimens,
        colorScheme = colorScheme,
        sheetState = sheetState,
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = onSheetShowChange,
        shapeMode = ShapeMode.ROUNDED
    ) {

    }
}