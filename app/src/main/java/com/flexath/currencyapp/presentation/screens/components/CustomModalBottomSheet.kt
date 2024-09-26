package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flexath.currencyapp.ui.theme.CurrencyColorScheme
import com.flexath.currencyapp.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottomSheet(
    modifier: Modifier = Modifier,
    dimens: Dimensions,
    colorScheme: CurrencyColorScheme,
    sheetState: SheetState,
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    shapeMode: ShapeMode = ShapeMode.ROUNDED,
    content: @Composable () -> Unit
) {
    if(bottomSheetShow) {
        ModalBottomSheet(
            modifier = modifier,
            sheetState = sheetState,
            shape = if(shapeMode == ShapeMode.SQUARED) {
                CutCornerShape(0.dp)
            } else {
                RoundedCornerShape(
                    topStart = dimens.mediumPadding5,
                    topEnd = dimens.mediumPadding5
                )
            },
            tonalElevation = dimens.smallPadding2,
            containerColor = if(shapeMode == ShapeMode.SQUARED) {
                MaterialTheme.colorScheme.background
            } else {
                colorScheme.colorCardBackground
            },
            onDismissRequest = {
                onSheetShowChange(false)
            }
        ) {
            content()
        }
    }
}

enum class ShapeMode {
    ROUNDED,
    SQUARED
}