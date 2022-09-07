package com.example.cruptoappmy.presentor.screeens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cruptoappmy.APP
import com.example.cruptoappmy.R
import com.example.cruptoappmy.presentor.CoinsAdapter
import com.example.cruptoappmy.databinding.FragmentFirstBinding
import com.example.cruptoappmy.pojo.CoinPriceInfo
import com.example.cruptoappmy.presentor.CoinViewModel

class FirstFragment : Fragment() {
    lateinit var binding: FragmentFirstBinding
    lateinit var adapter: CoinsAdapter
    lateinit var rcView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        adapter = CoinsAdapter()
        rcView = binding.rcView
        rcView.adapter = adapter
        adapter.clickInterface = object : CoinsAdapter.clickItem {
            override fun onClick(coinPriceInfo: CoinPriceInfo) {
                val bundle = Bundle()
                bundle.putString("name", coinPriceInfo.fromsymbol)
                APP.navController.navigate(R.id.action_firstFragment_to_detailFragment, bundle)
            }

        }
        viewModel.getCoinPrises.observe(viewLifecycleOwner) {
            adapter.listAd = it
        }
    }

}