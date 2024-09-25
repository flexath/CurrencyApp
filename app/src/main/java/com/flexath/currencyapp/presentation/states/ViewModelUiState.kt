package com.flexath.currencyapp.presentation.states

data class ViewModelUiState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)