package com.example.cruptoappmy.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cruptoappmy.R
import com.example.cruptoappmy.databinding.ItemCoinInfoBinding
import com.example.cruptoappmy.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinsAdapter : RecyclerView.Adapter<CoinsAdapter.CoinsViewHolder>() {
    var listAd: List<CoinPriceInfo> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinsViewHolder(view)
    }

    var clickInterface: clickItem? = null


    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val coin = listAd[position]
        holder.tvSymbols.text = coin.fromsymbol.toString() + " / " + coin.tosymbol.toString()
        holder.lastUpdate.text = coin.getFormattedTime()
        holder.price.text = coin.price.toString()
        Picasso.get().load(coin.getImageURL()).into(holder.ivLogoCoin)
        holder.itemView.setOnClickListener {
            clickInterface?.onClick(listAd[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listAd.size
    }


    class CoinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCoinInfoBinding.bind(itemView)
        val tvSymbols = binding.tvSymbols
        val price = binding.price
        val lastUpdate = binding.lastUpdate
        val ivLogoCoin = binding.ivLogoCoin


    }

    interface clickItem {
        fun onClick(coinPriceInfo: CoinPriceInfo)
    }

}