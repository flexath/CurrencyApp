package com.flexath.currencyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexath.currencyapp.data.remote.paging.CurrencyRemoteDataKeys

@Dao
interface RemoteDataKeyDao {
    @Query("SELECT * FROM currency_remote_key_table WHERE uniqueId =:uniqueId")
    suspend fun getCurrencyRemoteKeys(uniqueId: Long): CurrencyRemoteDataKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrencyAllRemoteKeys(remoteKeys: List<CurrencyRemoteDataKeys>)

    @Query("DELETE FROM currency_remote_key_table")
    suspend fun clearCurrencyAllRemoteKeys()
}