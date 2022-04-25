package com.example.cruptoappmy.api

import com.example.cruptoappmy.pojo.CoinInfoListOfData
import com.example.cruptoappmy.pojo.CoinPriceInfoRawData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    suspend fun getTopCoinList(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_LIMIT) limit: Int = 10,
        @Query(QUERY_TSYM) tsym: String = CURRENCY_C
    ): Response<CoinInfoListOfData>

    @GET("pricemultifull")
    suspend fun getCoinPrice(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_FSUMS) fsyms: String,
        @Query(QUERY_TSUMS) tsyms: String = CURRENCY_C
    ): Response<CoinPriceInfoRawData>


    companion object {
        private const val QUERY_API_KEY = "api_key"
        private const val QUERY_LIMIT = "limit"
        private const val QUERY_TSYM = "tsym"
        private const val QUERY_FSUMS = "fsyms"
        private const val QUERY_TSUMS = "tsyms"

        private const val API_KEY = ""
        private const val CURRENCY_C = "USD"


    }


}
