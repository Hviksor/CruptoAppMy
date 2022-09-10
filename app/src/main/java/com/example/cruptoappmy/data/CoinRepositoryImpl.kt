package com.example.cruptoappmy.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cruptoappmy.data.database.AppDatabase
import com.example.cruptoappmy.data.mapper.CoinMapper
import com.example.cruptoappmy.data.network.ApiFactory
import com.example.cruptoappmy.data.workers.RefreshDataWorker
import com.example.cruptoappmy.data.workers.RefreshDataWorker.Companion.WORKER_NAME
import com.example.cruptoappmy.domain.CoinInfoEntity
import com.example.cruptoappmy.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(private val application: Application) : CoinRepository {
    private val coinDao = AppDatabase.getInstance(application).getDao()
    private val coinMapper = CoinMapper()


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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            WORKER_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.getOneTimeWorkRequest()
        )


    }
}