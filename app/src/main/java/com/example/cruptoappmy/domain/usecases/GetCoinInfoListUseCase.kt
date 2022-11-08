package com.example.cruptoappmy.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cruptoappmy.data.repository.CoinRepositoryImpl
import com.example.cruptoappmy.domain.entity.CoinInfoEntity

class GetCoinInfoListUseCase(private val repo: CoinRepositoryImpl) {
    operator fun invoke(): LiveData<List<CoinInfoEntity>> {
        return repo.getCoinInfoList()
    }
}