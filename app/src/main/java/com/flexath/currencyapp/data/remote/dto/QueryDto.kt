package com.flexath.currencyapp.data.remote.dto

import com.flexath.currencyapp.domain.model.QueryVO
import com.google.gson.annotations.SerializedName

data class QueryDto(
    @SerializedName("amount")
    val amount: Long?,

    @SerializedName("from")
    val from: String?,

    @SerializedName("to")
    val to: String?
) {
    fun toQueryVO(): QueryVO {
        return QueryVO(
            from = from,
            to = to,
            amount = amount
        )
    }
}