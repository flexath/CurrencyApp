package com.flexath.currencyapp.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatAsTime(timestamp: Long): String? {
        val instant = Instant.ofEpochSecond(timestamp)

        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            .withZone(ZoneId.systemDefault())

        return formatter.format(instant)
    }
}