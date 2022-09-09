package com.example.cruptoappmy.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cruptoappmy.data.database.AppDatabase
import com.example.cruptoappmy.data.mapper.CoinMapper
import com.example.cruptoappmy.data.network.ApiFactory
import com.example.cruptoappmy.domain.CoinInfoEntity
import com.example.cruptoappmy.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(application: Application) : CoinRepository {
    private val coinDao = AppDatabase.getInstance(application).getDao()
    private val coinMapper = CoinMapper()
    private val api = ApiFactory.api


    override fun getCoinInfoList(): LiveData<List<CoinInfoEntity>> {
        return Transformations.map(coinDao.getCoinPriceList()) {
            it.map { coinMapper.mapDBtoEntity(it) }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity> {
        return Transformations.map(coinDao.getSingleCoinInfo(fromSymbol)) {
            coinMapper.mapDBtoEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoinList = api.getTopCoinList(limit = 50)
                val fsyms = coinMapper.mapNamesListToString(topCoinList)
                val jsonContainer = api.getCoinPrice(fsyms = fsyms)
                val coinInfoDbList = coinMapper.mapJsonContainerToCoinInfoDbList(jsonContainer)
                coinDao.insertCoinPriceList(coinInfoDbList)
            } catch (e: Exception) {

            }
            delay(5000)
        }

    }
}