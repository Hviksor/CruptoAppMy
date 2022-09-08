package com.example.cruptoappmy.domain

import android.os.Parcel
import android.os.Parcelable
import com.example.cruptoappmy.data.network.ApiFactory
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinInfoEntity(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: String?,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val imageUrl: String?
):Parcelable