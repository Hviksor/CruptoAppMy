package com.example.cruptoappmy.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cruptoappmy.data.repository.CoinRepositoryImpl
import com.example.cruptoappmy.domain.entity.CoinInfoEntity

class GetCoinInfoUseCase(private val repo: CoinRepositoryImpl) {
    operator fun invoke(fromSymbol: String): LiveData<CoinInfoEntity> {
        return repo.getCoinInfo(fromSymbol)
    }
}