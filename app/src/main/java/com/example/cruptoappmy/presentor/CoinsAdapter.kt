package com.example.cruptoappmy.presentor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cruptoappmy.databinding.ItemCoinInfoBinding
import com.example.cruptoappmy.domain.CoinInfoEntity
import com.squareup.picasso.Picasso

class CoinsAdapter : ListAdapter<CoinInfoEntity, CoinsViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CoinsViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinsViewHolder(binding)
    }

    var onClick: ((CoinInfoEntity) -> Unit)? = null

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            tvSymbols
            tvSymbols.text = coin.fromSymbol + " / " + coin.toSymbol
            lastUpdate.text = coin.lastUpdate
            price.text = coin.price.toString()
            Picasso.get().load(coin.imageUrl).into(ivLogoCoin)
            root.setOnClickListener {
                onClick?.invoke(coin)
            }
        }
    }

}