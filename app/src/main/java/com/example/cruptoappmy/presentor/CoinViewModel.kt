package com.example.cruptoappmy.presentor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cruptoappmy.data.CoinRepositoryImpl
import com.example.cruptoappmy.domain.GetCoinInfoListUseCase
import com.example.cruptoappmy.domain.GetCoinInfoUseCase
import com.example.cruptoappmy.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)


    init {
        viewModelScope.launch {
            loadDataUseCase()
        }

    }

    val coinInfoList = getCoinInfoListUseCase()
    fun getDetailCoinInfo(fSym: String) = getCoinInfoUseCase(fSym)


}