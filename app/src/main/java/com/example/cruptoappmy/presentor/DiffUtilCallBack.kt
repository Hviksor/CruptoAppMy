package com.example.cruptoappmy.presentor

import androidx.recyclerview.widget.DiffUtil
import com.example.cruptoappmy.domain.CoinInfoEntity

class DiffUtilCallBack : DiffUtil.ItemCallback<CoinInfoEntity>() {
    override fun areItemsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem == newItem
    }
}