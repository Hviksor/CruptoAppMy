package com.example.cruptoappmy.api

import android.util.Log
import com.example.cruptoappmy.pojo.CoinInfoListOfData
import com.example.cruptoappmy.pojo.CoinPriceInfoRawData
import retrofit2.Response

class Repository {

    suspend fun getTopCoinList(): Response<CoinInfoListOfData>{

        return ApiFactory.api.getTopCoinList()
    }

    suspend fun getCoinPrice(fsyms: String?): Response<CoinPriceInfoRawData> {
        return ApiFactory.api.getCoinPrice(fsyms = fsyms)
    }
}