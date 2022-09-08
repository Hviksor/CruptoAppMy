package com.example.cruptoappmy.presentor.screeens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cruptoappmy.presentor.CoinsAdapter
import com.example.cruptoappmy.databinding.FragmentFirstBinding
import com.example.cruptoappmy.domain.CoinInfoEntity
import com.example.cruptoappmy.presentor.CoinViewModel

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding = null")
    private lateinit var adapter: CoinsAdapter
    private lateinit var rcView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        adapter = CoinsAdapter()
        rcView = binding.rcView
        rcView.adapter = adapter
        adapter.onClick = {
            launchNextFragment(it)
        }

        viewModel.coinInfoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    fun launchNextFragment(coinInfoEntity: CoinInfoEntity) {
        findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToDetailFragment(coinInfoEntity))

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}