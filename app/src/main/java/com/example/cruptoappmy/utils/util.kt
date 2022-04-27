package com.example.cruptoappmy.utils

import java.sql.Date
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun getFormatedTime(lastUpd: Long?): String {

    val stamp = Timestamp(lastUpd?.times(1000) ?: 1)
    val date = Time(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)

}
