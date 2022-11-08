package com.example.cruptoappmy.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cruptoappmy.databinding.ItemCoinInfoBinding
import com.example.cruptoappmy.domain.entity.CoinInfoEntity
import com.squareup.picasso.Picasso

class CoinsAdapter :
    ListAdapter<CoinInfoEntity, CoinInfoViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    var onClick: ((CoinInfoEntity) -> Unit)? = null

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            tvCoinNameShort.text = coin.fromSymbol + " / " + coin.toSymbol
            lastUpdate.text = coin.lastUpdate
            tvCurrentPrice.text = coin.price.toString()
            Picasso.get().load(coin.imageUrl).into(ivLogoCoin)
            root.setOnClickListener {
                onClick?.invoke(coin)
            }
        }
    }

}