package com.example.cruptoappmy.data.network

import com.example.cruptoappmy.data.network.model.CoinNamesListDto
import com.example.cruptoappmy.data.network.model.CoinJsonContainerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    suspend fun getTopCoinList(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_LIMIT) limit: Int = 10,
        @Query(QUERY_TSYM) tsym: String = CURRENCY_C
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getCoinPrice(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_FSUMS) fsyms: String?,
        @Query(QUERY_TSUMS) tsyms: String = CURRENCY_C
    ): CoinJsonContainerDto


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
