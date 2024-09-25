package com.flexath.currencyapp.data.remote.dto

import com.flexath.currencyapp.domain.model.CurrencyEntity
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.google.gson.annotations.SerializedName

data class RealTimeRateResponse(
    @SerializedName("success")
    val success: Boolean?,

    @SerializedName("terms")
    val terms: String?,

    @SerializedName("privacy")
    val privacy: String?,

    @SerializedName("timestamp")
    val timestamp: Long?,

    @SerializedName("source")
    val source: String?,

    @SerializedName("quotes")
    val quotes: Map<String, Double>?
) {
    fun toCurrencyEntity(): CurrencyEntity {
        return CurrencyEntity(
            success = success,
            terms = terms,
            privacy = privacy,
            timestamp = timestamp,
            source = source,
            quotes = quotes?.toCurrencyVOList()
        )
    }
}

// Extension function to convert Map<String, Double> to List<CurrencyEntity>
fun Map<String, Double>.toCurrencyVOList(): List<CurrencyVO> {
    return this.map { (key, value) ->
        CurrencyVO(currencyCode = key, value = value)
    }
}