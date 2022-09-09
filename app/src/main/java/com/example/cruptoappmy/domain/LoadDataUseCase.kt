package com.example.cruptoappmy.domain

import com.example.cruptoappmy.data.CoinRepositoryImpl

class LoadDataUseCase(private val repo: CoinRepositoryImpl) {
    suspend operator fun invoke() {
        repo.loadData()
    }
}