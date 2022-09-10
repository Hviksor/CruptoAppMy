package com.example.cruptoappmy.domain

import com.example.cruptoappmy.data.CoinRepositoryImpl

class LoadDataUseCase(private val repo: CoinRepositoryImpl) {
    operator fun invoke() {
        repo.loadData()
    }
}