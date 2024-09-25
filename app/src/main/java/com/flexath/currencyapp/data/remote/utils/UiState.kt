package com.flexath.currencyapp.data.remote.utils

sealed class UiState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): UiState<T>(data)
    class Success<T>(data: T?): UiState<T>(data)
    class Error<T>(message: String, data: T? = null): UiState<T>(data, message)
}

sealed class SpecificUiState<T>(val data: T? = null, val errors: SpecificErrorResponse? = null) {
    class Loading<T>(data: T? = null): SpecificUiState<T>(data)
    class Success<T>(data: T?): SpecificUiState<T>(data)
    class Error<T>(errors: SpecificErrorResponse?, data: T? = null): SpecificUiState<T>(data, errors)
}