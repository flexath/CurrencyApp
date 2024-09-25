package com.flexath.currencyapp.data.remote.dto

import com.flexath.currencyapp.domain.model.InfoVO
import com.google.gson.annotations.SerializedName

data class InfoDto(

    @SerializedName("quote")
    val quote: Double?,

    @SerializedName("timestamp")
    val timestamp: Long?
) {
    fun toInfoVO(): InfoVO {
        return InfoVO(
            quote = quote,
            timestamp = timestamp
        )
    }
}