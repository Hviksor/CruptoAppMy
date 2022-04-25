package com.example.cruptoappmy.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cruptoappmy.api.Repository
import com.example.cruptoappmy.database.AppDatabase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application).getDao()
    private val repo = Repository()

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler){

            val coinString = repo.getTopCoinList().body()?.data?.map { it.coinInfo?.name }?.joinToString(",")

            Log.e("Test","$coinString")

        }


    }

}