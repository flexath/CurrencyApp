package com.flexath.currencyapp.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flexath.currencyapp.data.local.db.CurrencyDb
import com.flexath.currencyapp.data.remote.api.CurrencyApi
import com.flexath.currencyapp.domain.model.CurrencyVO

class RealTimeRatesPagingSource(
    private val currencyApi: CurrencyApi,
    private val currencyDb: CurrencyDb,
    private val currencies: String,
    private val source: String
) : PagingSource<Int, CurrencyVO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CurrencyVO> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = currencyApi.getRealTimeRates(
                currencies = currencies,
                source = source
            )

            val currencyList = response.quotes?.map { (key, value) ->
                CurrencyVO(
                    currencyCode = key.substring(3), // Extract currency code from key
                    value = value
                )
            }

            LoadResult.Page(
                data = currencyList.orEmpty(),
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = nextPageNumber + 1 // Assuming the API supports pagination
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CurrencyVO>): Int? {
        // Not needed for this example since we're not using a remote mediator
        return null
    }
}