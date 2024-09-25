package com.flexath.currencyapp.data.remote.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_remote_key_table")
data class CurrencyRemoteDataKeys(
    @PrimaryKey(autoGenerate = false)
    val uniqueId: Long = 0L,
    val id: String?,
    val prevPage: Int?,
    val nextPage: Int?
)