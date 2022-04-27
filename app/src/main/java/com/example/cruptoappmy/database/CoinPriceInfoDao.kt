package com.example.cruptoappmy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cruptoappmy.pojo.CoinInfo
import com.example.cruptoappmy.pojo.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinPriceList(list: List<CoinPriceInfo>)

    @Query("SELECT * FROM full_price_info ORDER by lastupdate")
    fun getCoinPriceList(): LiveData<List<CoinPriceInfo>>

    @Query("SELECT * FROM full_price_info WHERE fromsymbol==:coinName LIMIT 1 ")
    fun getSingleCoinInfo(coinName: String): LiveData<CoinPriceInfo>

}