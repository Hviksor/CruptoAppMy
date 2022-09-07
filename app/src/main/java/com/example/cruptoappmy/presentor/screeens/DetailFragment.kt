package com.example.cruptoappmy.presentor.screeens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.example.cruptoappmy.APP
import com.example.cruptoappmy.R
import com.example.cruptoappmy.databinding.FragmentDetailBinding
import com.example.cruptoappmy.presentor.CoinViewModel
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            APP.navController.navigate(R.id.action_detailFragment_to_firstFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        val coinName = arguments?.getString("name")
        coinName?.let { viewModel.getDetailInformation(it) }?.observe(viewLifecycleOwner) {
            Picasso.get().load(it.getImageURL()).into(binding.ivLogoCoin)
            binding.tvFromSymbol.text = it.fromsymbol
            binding.tvMinPrice.text = it.lowday.toString()
            binding.tvMaxPrice.text = it.highday.toString()
            binding.tvLastMarket.text = it.lastmarket
            binding.tvPrice.text = it.price.toString()
            binding.tvLastUpdate.text = it.getFormattedTime()
        }


    }

}