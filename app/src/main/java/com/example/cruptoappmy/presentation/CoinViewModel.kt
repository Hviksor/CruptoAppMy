package com.example.cruptoappmy.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cruptoappmy.data.repository.CoinRepositoryImpl
import com.example.cruptoappmy.domain.usecases.GetCoinInfoListUseCase
import com.example.cruptoappmy.domain.usecases.GetCoinInfoUseCase
import com.example.cruptoappmy.domain.usecases.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    init {
        loadDataUseCase()
    }

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailCoinInfo(fSym: String) = getCoinInfoUseCase(fSym)


}