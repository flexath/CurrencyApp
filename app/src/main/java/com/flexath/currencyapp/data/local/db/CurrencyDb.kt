package com.flexath.currencyapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexath.currencyapp.data.local.dao.CurrencyDao
import com.flexath.currencyapp.domain.model.CurrencyEntity
import com.flexath.currencyapp.domain.model.SupportedCurrencyVO

@Database(
    entities = [CurrencyEntity::class, SupportedCurrencyVO::class],
    version = 1,
    exportSchema = false
)
abstract class CurrencyDb: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}