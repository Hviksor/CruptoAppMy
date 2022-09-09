package com.example.cruptoappmy.data.mapper

import com.example.cruptoappmy.data.database.CoinInfoDbModel
import com.example.cruptoappmy.data.network.ApiFactory
import com.example.cruptoappmy.data.network.model.CoinInfoDto
import com.example.cruptoappmy.data.network.model.CoinJsonContainerDto
import com.example.cruptoappmy.data.network.model.CoinNamesListDto
import com.example.cruptoappmy.domain.CoinInfoEntity
import com.google.gson.Gson
import java.sql.Time
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CoinMapper {

    fun mapDBtoEntity(coinInfoDbModel: CoinInfoDbModel): CoinInfoEntity {
        return CoinInfoEntity(
            fromSymbol = coinInfoDbModel.fromSymbol,
            toSymbol = coinInfoDbModel.toSymbol,
            price = coinInfoDbModel.price,
            lastUpdate = getFormattedTime(coinInfoDbModel.lastUpdate),
            highDay = coinInfoDbModel.highDay,
            lowDay = coinInfoDbModel.lowDay,
            lastMarket = coinInfoDbModel.lastMarket,
            imageUrl = coinInfoDbModel.imageUrl
        )

    }

    private fun getImageURL(imageUrl: String?): String {
        return BASE_IMG_URL + imageUrl
    }

    private fun getFormattedTime(lastUpd: Long?): String {
        val stamp = Timestamp(lastUpd?.times(1000) ?: 1)
        val date = Time(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)

    }

    private fun mapDtoToDb(coinInfoDto: CoinInfoDto): CoinInfoDbModel {
        return CoinInfoDbModel(
            fromSymbol = coinInfoDto.fromsymbol,
            toSymbol = coinInfoDto.tosymbol,
            price = coinInfoDto.price,
            lastUpdate = coinInfoDto.lastupdate,
            highDay = coinInfoDto.highday,
            lowDay = coinInfoDto.lowday,
            lastMarket = coinInfoDto.lastmarket,
            imageUrl = getImageURL(coinInfoDto.imageurl)
        )
    }


    fun mapNamesListToString(topCoinList: CoinNamesListDto): String? {
        return topCoinList.data?.map { it.coinName?.name }?.joinToString(",")
    }

    fun mapJsonContainerToCoinInfoDbList(jsonContainer: CoinJsonContainerDto): List<CoinInfoDbModel> {
        val listResult = ArrayList<CoinInfoDbModel>()
        val coinJsonObject = jsonContainer.CoinPriceJsonObject?.asJsonObject
        val keySet = coinJsonObject?.keySet()
        if (keySet != null) {
            for (key in keySet) {
                val newCoinJsonObject = coinJsonObject.getAsJsonObject(key)
                val newKeySet = newCoinJsonObject.keySet()
                for (newKey in newKeySet) {
                    val coinInfoDto = Gson().fromJson(newCoinJsonObject.getAsJsonObject(newKey), CoinInfoDto::class.java)
                    val coinInfoDb = mapDtoToDb(coinInfoDto)
                    listResult.add(coinInfoDb)
                }
            }
        }
        return listResult
    }


    companion object {

        const val BASE_IMG_URL = "https://www.cryptocompare.com/"
    }


}