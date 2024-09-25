package com.flexath.currencyapp.domain.repository

import com.flexath.currencyapp.data.local.db.CurrencyDb
import com.flexath.currencyapp.data.remote.api.CurrencyApi
import com.flexath.currencyapp.data.remote.utils.SpecificApiErrorResponse
import com.flexath.currencyapp.data.remote.utils.SpecificErrorResponse
import com.flexath.currencyapp.data.remote.utils.SpecificUiState
import com.flexath.currencyapp.data.repository.CurrencyRepository
import com.flexath.currencyapp.domain.model.CurrencyConverterVO
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val currencyDb: CurrencyDb
) : CurrencyRepository {
    override fun getRealTimeRates(
        currencies: String?,
        source: String?
    ): Flow<SpecificUiState<List<CurrencyVO>>> = flow {
        emit(SpecificUiState.Loading())

        try {
            val response = currencyApi.getRealTimeRates(
                currencies = currencies,
                source = source
            )
            val currencyList = response.quotes?.map { (key, value) ->
                CurrencyVO(
                    currencyCode = key.substring(3),
                    value = value
                )
            }
            emit(SpecificUiState.Success(currencyList))
        } catch (e: HttpException) {
            val apiError = SpecificApiErrorResponse(e)
            emit(
                SpecificUiState.Error(
                    errors = apiError.errors,
                    data = listOf()
                )
            )
        } catch (e: IOException) {
            val apiError = SpecificApiErrorResponse(e)
            emit(
                SpecificUiState.Error(
                    errors = SpecificErrorResponse(
                        message = "Couldn't reach server, check your internet connection.",
                        code = apiError.errors?.code
                    ),
                    data = listOf()
                )
            )
        }
    }

    override fun getSupportedCurrencies(): Flow<SpecificUiState<List<SupportedCurrencyVO>>> = flow {
        emit(SpecificUiState.Loading())

        try {
            val response = currencyApi.getSupportedCurrencies()
            val supportedCurrencyList = response.currencies?.map { (key, value) ->
                SupportedCurrencyVO(
                    currencyCode = key,
                    value = value
                )
            }
            emit(SpecificUiState.Success(supportedCurrencyList))
        } catch (e: HttpException) {
            val apiError = SpecificApiErrorResponse(e)
            emit(
                SpecificUiState.Error(
                    errors = apiError.errors,
                    data = listOf()
                )
            )
        } catch (e: IOException) {
            val apiError = SpecificApiErrorResponse(e)
            emit(
                SpecificUiState.Error(
                    errors = SpecificErrorResponse(
                        message = "Couldn't reach server, check your internet connection.",
                        code = apiError.errors?.code
                    ),
                    data = listOf()
                )
            )
        }
    }

    override fun convertCurrency(
        from: String,
        to: String,
        amount: Double
    ): Flow<SpecificUiState<CurrencyConverterVO>> = flow {
        emit(SpecificUiState.Loading())

        try {
            val response = currencyApi.convertCurrency(
                from = from,
                to = to,
                amount = amount
            )
            val supportedCurrencyList = response.toCurrencyConverterVO()
            emit(SpecificUiState.Success(supportedCurrencyList))
        } catch (e: HttpException) {
            val apiError = SpecificApiErrorResponse(e)
            emit(
                SpecificUiState.Error(
                    errors = apiError.errors,
                    data = null
                )
            )
        } catch (e: IOException) {
            val apiError = SpecificApiErrorResponse(e)
            emit(
                SpecificUiState.Error(
                    errors = SpecificErrorResponse(
                        message = "Couldn't reach server, check your internet connection.",
                        code = apiError.errors?.code
                    ),
                    data = null
                )
            )
        }
    }
}