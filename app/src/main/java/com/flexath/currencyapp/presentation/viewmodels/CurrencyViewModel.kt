package com.flexath.currencyapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.gamingservices.cloudgaming.CloudGameLoginHandler.init
import com.flexath.currencyapp.data.remote.utils.SpecificUiState
import com.flexath.currencyapp.domain.model.CurrencyConverterVO
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import com.flexath.currencyapp.domain.usecase.CurrencyUseCases
import com.flexath.currencyapp.presentation.states.ViewModelUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
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
    val supportedCurrencies get() = _supportedCurrencies.asStateFlow()

    // For Convert Currency
    private var _convertCurrency = MutableStateFlow(ViewModelUiState<CurrencyConverterVO>())
    val convertCurrency get() = _convertCurrency.asStateFlow()

    private var _currencyFromStateflow =  MutableStateFlow("")
    val currencyFromStateFlow = _currencyFromStateflow.asStateFlow()

    private var _currencyToStateflow =  MutableStateFlow("")
    val currencyToStateflow = _currencyToStateflow.asStateFlow()

    private var _amountStateflow =  MutableStateFlow("")
    val amountStateflow = _amountStateflow.asStateFlow()

    private var _isCallRealTimeRates = MutableStateFlow(false)
    val isCallRealTimeRates get() = _isCallRealTimeRates.asStateFlow()

    init {
        //getRealTimeRates()          // I want to call that api because its default currency is USD which my app needs on first launch
        getSupportedCurrencies()
    }

    fun updateIsCallRealTimeRates(isCalled: Boolean) {
        _isCallRealTimeRates.update {
            isCalled
        }
    }

    fun updateCurrencyFrom(currencyFrom: String) {
        _currencyFromStateflow.update {
            currencyFrom
        }
    }

    fun updateCurrencyTo(currencyTo: String) {
        _currencyToStateflow.update {
            currencyTo
        }
    }

    fun updateAmount(amount: String) {
        _amountStateflow.update {
            amount
        }
    }

    fun getRealTimeRates(
        currencies: String? = null,
        source: String? = null
    ) {
        viewModelScope.launch {
            currencyUseCases.getRealTimeRatesUseCase(
                currencies = currencies,
                source = source
            ).flowOn(Dispatchers.IO)
                .distinctUntilChanged()
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
                            updateIsCallRealTimeRates(true)
                        }
                    }
                }
        }
    }

    fun getSupportedCurrencies() {
        viewModelScope.launch {
            currencyUseCases.getSupportedCurrenciesUseCase()
                .flowOn(Dispatchers.IO)
                .collectLatest { uiState ->
                    when(uiState) {
                        is SpecificUiState.Error -> {
                            _supportedCurrencies.update {
                                it.copy(
                                    data = uiState.data,
                                    isLoading = false,
                                    isError = true,
                                    errorMessage = uiState.errors?.message
                                )
                            }
                        }
                        is SpecificUiState.Loading -> {
                            _supportedCurrencies.update {
                                it.copy(
                                    data = uiState.data,
                                    isLoading = true,
                                    isError = false,
                                    errorMessage = null
                                )
                            }
                        }
                        is SpecificUiState.Success -> {
                            _supportedCurrencies.update {
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

    fun convertCurrency(
        from: String,
        to: String,
        amount: String
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