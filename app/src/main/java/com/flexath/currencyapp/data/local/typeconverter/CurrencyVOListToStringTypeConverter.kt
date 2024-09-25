package com.flexath.currencyapp.data.local.typeconverter

import androidx.room.TypeConverter
import com.flexath.currencyapp.domain.model.CurrencyVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrencyVOListToStringTypeConverter {
    @TypeConverter
    fun toString(list: List<CurrencyVO>?) :String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(jsonString:String) : List<CurrencyVO>? {
        val list = object : TypeToken<List<CurrencyVO>?>(){}.type
        return Gson().fromJson(jsonString,list)
    }
}