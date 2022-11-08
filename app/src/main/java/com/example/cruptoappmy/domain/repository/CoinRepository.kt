package com.example.cruptoappmy.domain.repository

import androidx.lifecycle.LiveData
import com.example.cruptoappmy.domain.entity.CoinInfoEntity

interface CoinRepository {
    fun getCoinInfoList(): LiveData<List<CoinInfoEntity>>
    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity>
    fun loadData()

}