package com.example.cruptoappmy.data.workers

import android.content.Context
import androidx.work.*
import com.example.cruptoappmy.data.database.AppDatabase
import com.example.cruptoappmy.data.mapper.CoinMapper
import com.example.cruptoappmy.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    private val coinDao = AppDatabase.getInstance(context).getDao()
    private val coinMapper = CoinMapper()
    private val apiService = ApiFactory.api

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoinList = apiService.getTopCoinList(limit = 50)
                val fsyms = coinMapper.mapNamesListToString(topCoinList)
                val jsonContainer = apiService.getCoinPrice(fsyms = fsyms)
                val coinInfoDbList = coinMapper.mapJsonContainerToCoinInfoDbList(jsonContainer)
                coinDao.insertCoinPriceList(coinInfoDbList)
            } catch (e: Exception) {
            }
            delay(5000)
        }
    }

    companion object {
        const val WORKER_NAME = "RefreshDataWorker"

        fun getOneTimeWorkRequest() =
            OneTimeWorkRequestBuilder<RefreshDataWorker>()
                .setConstraints(getConstraint())
                .build()

        private fun getConstraint(): Constraints {
            return Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        }

    }
}