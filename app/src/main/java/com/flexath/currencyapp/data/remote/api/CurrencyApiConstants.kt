package com.flexath.currencyapp.data.remote.api

object CurrencyApiConstants {
    const val BASE_URL = "https://api.currencylayer.com/"

    // QUERY PARAMS
    const val QUERY_ACCESS_KEY = "access_key"
    const val QUERY_CURRENCIES = "currencies"
    const val QUERY_SOURCE = "source"

    const val QUERY_FROM ="from"
    const val QUERY_TO = "to"
    const val QUERY_AMOUNT = "amount"
}

object CurrencyDbConstants {
    const val DB_NAME = "currency_db"
}

const val PAGING_PAGE_SIZE = 10