package com.flexath.currencyapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.flexath.currencyapp.data.local.typeconverter.CurrencyVOListToStringTypeConverter

@Entity("currency_table")
@TypeConverters(
    CurrencyVOListToStringTypeConverter::class
)
data class CurrencyEntity(
    val success: Boolean?,
    val terms: String?,
    val privacy: String?,
    val timestamp: Long?,
    val source: String?,
    val quotes: List<CurrencyVO>?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
