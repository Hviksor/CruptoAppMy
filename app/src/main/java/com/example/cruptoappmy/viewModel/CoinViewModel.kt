package com.example.cruptoappmy.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cruptoappmy.api.Repository
import com.example.cruptoappmy.database.AppDatabase
import com.example.cruptoappmy.pojo.CoinPriceInfo
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application).getDao()
    private val repo = Repository()
    val getCoinPrises = db.getCoinPriceList()


    init {
        getCoinPriceList()
    }

    fun getDetailInformation(name: String): LiveData<CoinPriceInfo> {
        return db.getSingleCoinInfo(name)

    }


    private fun getCoinPriceList() {
        viewModelScope.launch(Dispatchers.IO) {
            repeat(5000) {
                val listResult = ArrayList<CoinPriceInfo>()
                val coinsString = repo.getTopCoinList().body()?.data?.map { it.coinInfo?.name }?.joinToString(",")
                val coinJsonObject = repo.getCoinPrice(coinsString).body()?.CoinPriceJsonObject?.asJsonObject
                Log.e("Test", "$coinJsonObject")
                val keySet = coinJsonObject?.keySet()
                if (keySet != null) {
                    for (key in keySet) {
                        val newCoinJsonObject = coinJsonObject.getAsJsonObject(key)
                        val newKeySet = newCoinJsonObject.keySet()
                        for (newKey in newKeySet) {
                            val coinPriceInfo = Gson().fromJson(newCoinJsonObject.getAsJsonObject(newKey), CoinPriceInfo::class.java)
                            listResult.add(coinPriceInfo)
                            delay(2000)
                            db.insertCoinPriceList(listResult)
                        }
                    }
                }


            }
        }


    }

}