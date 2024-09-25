package com.flexath.currencyapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexath.currencyapp.data.local.dao.CurrencyDao
import com.flexath.currencyapp.data.local.dao.RemoteDataKeyDao
import com.flexath.currencyapp.data.remote.paging.CurrencyRemoteDataKeys
import com.flexath.currencyapp.domain.model.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class, CurrencyRemoteDataKeys::class],
    version = 1,
    exportSchema = false
)
abstract class CurrencyDb: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun remoteDataKeyDao(): RemoteDataKeyDao
}