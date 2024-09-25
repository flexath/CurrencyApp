package com.flexath.currencyapp.data.remote.dto

import com.flexath.currencyapp.domain.model.CurrencyConverterVO
import com.google.gson.annotations.SerializedName

data class CurrencyConvertResponse(
    @SerializedName("info")
    val info: InfoDto?,

    @SerializedName("privacy")
    val privacy: String?,

    @SerializedName("query")
    val query: QueryDto?,

    @SerializedName("result")
    val result: Double?,

    @SerializedName("success")
    val success: Boolean?,

    @SerializedName("terms")
    val terms: String?
) {
    fun toCurrencyConverterVO(): CurrencyConverterVO {
        return CurrencyConverterVO(
            info = info?.toInfoVO(),
            privacy = privacy,
            query = query?.toQueryVO(),
            result = result,
            success = success,
            terms = terms
        )
    }
}