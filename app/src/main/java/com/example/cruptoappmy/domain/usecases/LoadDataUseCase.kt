package com.example.cruptoappmy.domain.usecases

import com.example.cruptoappmy.data.repository.CoinRepositoryImpl

class LoadDataUseCase(private val repo: CoinRepositoryImpl) {
    operator fun invoke() {
        repo.loadData()
    }
}