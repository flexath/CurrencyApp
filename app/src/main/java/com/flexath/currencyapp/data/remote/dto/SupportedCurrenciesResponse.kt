package com.flexath.currencyapp.data.remote.dto

import com.flexath.currencyapp.domain.model.CurrencyEntity
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.flexath.currencyapp.domain.model.SupportedCurrencyEntity
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO
import com.google.gson.annotations.SerializedName

data class SupportedCurrenciesResponse(
    @SerializedName("success")
    val success: Boolean?,

    @SerializedName("terms")
    val terms: String?,

    @SerializedName("privacy")
    val privacy: String?,

    @SerializedName("quotes")
    val currencies: Map<String, Double>?
) {
    fun toCurrencyEntity(): SupportedCurrencyEntity {
        return SupportedCurrencyEntity(
            success = success,
            terms = terms,
            privacy = privacy,
            currencies = currencies?.toSupportedCurrencyVOList()
        )
    }
}

// Extension function to convert Map<String, Double> to List<CurrencyEntity>
fun Map<String, Double>.toSupportedCurrencyVOList(): List<SupportedCurrencyVO> {
    return this.map { (key, value) ->
        SupportedCurrencyVO(currencyCode = key, value = value)
    }
}