package com.example.cruptoappmy.data.network.model

import com.example.cruptoappmy.data.network.model.CoinNameDto
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName





data class CoinNameContainerDto (
 @SerializedName("CoinInfo")
 @Expose
 val coinName: CoinNameDto? = null

 )