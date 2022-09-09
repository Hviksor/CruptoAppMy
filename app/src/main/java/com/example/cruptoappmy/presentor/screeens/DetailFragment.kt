package com.example.cruptoappmy.presentor.screeens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.cruptoappmy.databinding.FragmentDetailBinding
import com.example.cruptoappmy.domain.CoinInfoEntity
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var coinInfoEntity: CoinInfoEntity
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFields()
    }

    private fun bindFields() {
        coinInfoEntity = args.coiInfoEntity
        Picasso.get().load(coinInfoEntity.imageUrl).into(binding.ivLogoCoin)
        binding.tvFromSymbol.text = coinInfoEntity.fromSymbol
        binding.tvMinPrice.text = coinInfoEntity.lowDay.toString()
        binding.tvMaxPrice.text = coinInfoEntity.highDay.toString()
        binding.tvLastMarket.text = coinInfoEntity.lastMarket
        binding.tvPrice.text = coinInfoEntity.price.toString()
        binding.tvLastUpdate.text = coinInfoEntity.lastUpdate
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private v
    }

}