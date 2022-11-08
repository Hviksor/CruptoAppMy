package com.example.cruptoappmy.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.cruptoappmy.domain.entity.CoinInfoEntity

class DiffUtilCallBack : DiffUtil.ItemCallback<CoinInfoEntity>() {
    override fun areItemsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem == newItem
    }
}