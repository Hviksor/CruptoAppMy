package com.example.cruptoappmy.presentor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cruptoappmy.R
import com.example.cruptoappmy.data.network.ApiFactory.BASE_IMG_URL
import com.example.cruptoappmy.data.network.model.CoinInfoDto
import com.example.cruptoappmy.databinding.ItemCoinInfoBinding
import com.example.cruptoappmy.domain.CoinInfoEntity
import com.squareup.picasso.Picasso

class CoinsAdapter : ListAdapter<CoinInfoEntity, CoinsAdapter.CoinsViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinsViewHolder(view)
    }


    var onClick: ((CoinInfoEntity) -> Unit)? = null


    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val coin = getItem(position)
        holder.tvSymbols.text = coin.fromSymbol + " / " + coin.toSymbol
        holder.lastUpdate.text = coin.lastUpdate
        holder.price.text = coin.price.toString()
        Picasso.get().load(BASE_IMG_URL + coin.imageUrl).into(holder.ivLogoCoin)
        holder.itemView.setOnClickListener {
            onClick?.invoke(coin)
        }
    }

    class CoinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCoinInfoBinding.bind(itemView)
        val tvSymbols = binding.tvSymbols
        val price = binding.price
        val lastUpdate = binding.lastUpdate
        val ivLogoCoin = binding.ivLogoCoin


    }

    interface clickItem {
        fun onClick(coinInfoDto: CoinInfoDto)
    }

}