package com.flexath.currencyapp.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.flexath.currencyapp.R
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import com.flexath.currencyapp.presentation.states.ViewModelUiState
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
    title: String,
    supportedCurrenciesState: ViewModelUiState<List<SupportedCurrencyVO>>,
    sheetState: SheetState,
    bottomSheetShow: Boolean,
    onSheetShowChange: (Boolean) -> Unit,
    onSelected: (String) -> Unit,
) {
    val initialCurrency = supportedCurrenciesState.data?.firstOrNull()?.currencyCode.orEmpty()
    var selectedCurrency by rememberSaveable { mutableStateOf(initialCurrency) }

    CustomModalBottomSheet(
        modifier = modifier,
        dimens = dimens,
        colorScheme = colorScheme,
        sheetState = sheetState,
        bottomSheetShow = bottomSheetShow,
        onSheetShowChange = onSheetShowChange,
        shapeMode = ShapeMode.SQUARED
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            item {
                Text(
                    text = title,
                    style = typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = colorScheme.colorPrimary,
                    modifier = Modifier.padding(horizontal = dimens.mediumPadding3).fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(dimens.mediumPadding3))

                if(supportedCurrenciesState.isLoading) {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = colorScheme.colorPrimary,
                            strokeWidth = dimens.smallPadding1
                        )
                    }
                } else if(supportedCurrenciesState.isError && supportedCurrenciesState.data?.isEmpty() == true) {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_empty_state),
                            contentDescription = null,
                            modifier = Modifier.size(dimens.productCardWidth)
                        )
                    }
                }
            }

            supportedCurrencyList(
                modifier = Modifier.padding(horizontal = dimens.mediumPadding3).fillMaxWidth(),
                dimensions = dimens,
                colorScheme = colorScheme,
                typography = typography,
                currencyList = supportedCurrenciesState.data.orEmpty(),
                selectedCurrency = selectedCurrency,
                onSelected = {
                    selectedCurrency = it
                    onSelected(it)
                    onSheetShowChange(false)
                }
            )
        }
    }
}