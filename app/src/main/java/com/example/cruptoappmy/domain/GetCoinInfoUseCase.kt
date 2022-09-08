package com.example.cruptoappmy.domain

import androidx.lifecycle.LiveData
import com.example.cruptoappmy.data.CoinRepositoryImpl

class GetCoinInfoUseCase(private val repo: CoinRepositoryImpl) {
    operator fun invoke(fromSymbol: String): LiveData<CoinInfoEntity> {
        return repo.getCoinInfo(fromSymbol)
    }
}