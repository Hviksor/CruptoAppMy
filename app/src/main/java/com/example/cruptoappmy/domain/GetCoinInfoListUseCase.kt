package com.example.cruptoappmy.domain

import androidx.lifecycle.LiveData
import com.example.cruptoappmy.data.CoinRepositoryImpl

class GetCoinInfoListUseCase(private val repo: CoinRepositoryImpl) {
    operator fun invoke(): LiveData<List<CoinInfoEntity>> {
        return repo.getCoinInfoList()
    }
}