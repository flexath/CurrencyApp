package com.flexath.currencyapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.currencyapp.data.remote.utils.SpecificUiState
import com.flexath.currencyapp.domain.model.CurrencyConverterVO
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import com.flexath.currencyapp.domain.usecase.CurrencyUseCases
import com.flexath.currencyapp.presentation.states.ViewModelUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyUseCases: CurrencyUseCases
): ViewModel() {

    // For Realtime Rates
    private var _realTimeRates = MutableStateFlow(ViewModelUiState<List<CurrencyVO>>())
    val realTimeRates get() = _realTimeRates.asStateFlow()

    // For Supported Currencies
    private var _supportedCurrencies = MutableStateFlow(ViewModelUiState<List<SupportedCurrencyVO>>())
    val supportedCurrencies get() = _supportedCurrencies
        .onStart {
            getSupportedCurrencies()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = ViewModelUiState()
        )

    // For Convert Currency
    private var _convertCurrency = MutableStateFlow(ViewModelUiState<CurrencyConverterVO>())
    val convertCurrency get() = _convertCurrency.asStateFlow()

    fun getRealTimeRates(
        currencies: String? = null,
        source: String? = null
    ) {
        viewModelScope.launch {
            currencyUseCases.getRealTimeRatesUseCase(
                currencies = currencies,
                source = source
            ).flowOn(Dispatchers.IO)
                .collectLatest { uiState ->
                    when(uiState) {
                        is SpecificUiState.Error -> {
                            _realTimeRates.update {
                                it.copy(
                                    data = null,
                                    isLoading = false,
                                    isError = true,
                                    errorMessage = uiState.errors?.message
                                )
                            }
                        }
                        is SpecificUiState.Loading -> {
                            _realTimeRates.update {
                                it.copy(
                                    data = null,
                                    isLoading = true,
                                    isError = false,
                                    errorMessage = null
                                )
                            }
                        }
                        is SpecificUiState.Success -> {
                            _realTimeRates.update {
                                it.copy(
                                    data = uiState.data,
                                    isLoading = false,
                                    isError = false,
                                    errorMessage = null
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun getSupportedCurrencies() {
        viewModelScope.launch {
            currencyUseCases.getSupportedCurrenciesUseCase()
        }
    }

    fun convertCurrency(
        from: String,
        to: String,
        amount: Double
    ) {
        viewModelScope.launch {
            currencyUseCases.convertCurrencyUseCase(
                from = from,
                to = to,
                amount = amount
            ).flowOn(Dispatchers.IO)
                .collectLatest { uiState ->
                    when(uiState) {
                        is SpecificUiState.Error -> {
                            _convertCurrency.update {
                                it.copy(
                                    data = null,
                                    isLoading = false,
                                    isError = true,
                                    errorMessage = uiState.errors?.message
                                )
                            }
                        }
                        is SpecificUiState.Loading -> {
                            _convertCurrency.update {
                                it.copy(
                                    data = null,
                                    isLoading = true,
                                    isError = false,
                                    errorMessage = null
                                )
                            }
                        }
                        is SpecificUiState.Success -> {
                            _convertCurrency.update {
                                it.copy(
                                    data = uiState.data,
                                    isLoading = false,
                                    isError = false,
                                    errorMessage = null
                                )
                            }
                        }
                    }
                }
        }
    }
}