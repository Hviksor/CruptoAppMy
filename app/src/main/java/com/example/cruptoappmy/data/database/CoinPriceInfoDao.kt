package com.example.cruptoappmy.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cruptoappmy.data.network.model.CoinInfoDto

@Dao
interface CoinPriceInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinPriceList(list: List<CoinInfoDbModel>)

    @Query("SELECT * FROM full_price_info ORDER by lastupdate DESC")
    fun getCoinPriceList(): LiveData<List<CoinInfoDbModel>>

    @Query("SELECT * FROM full_price_info WHERE fromsymbol==:coinName LIMIT 1 ")
    fun getSingleCoinInfo(coinName: String): LiveData<CoinInfoDbModel>

}