package com.example.cruptoappmy.presentation.screeens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cruptoappmy.R
import com.example.cruptoappmy.presentation.CoinsAdapter
import com.example.cruptoappmy.databinding.FragmentFirstBinding
import com.example.cruptoappmy.presentation.CoinViewModel

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
        rcView.itemAnimator = null
        adapter.onClick = {
            launchNextFragment(it.fromSymbol)
        }
        viewModel.coinInfoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    fun launchNextFragment(fromSymbol: String) {
        val container = if (binding.fragmentContainerLand == null) {
            R.id.nav_container_first
        } else {
            R.id.fragmentContainerLand
        }

        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(container, DetailFragment.getFragment(fromSymbol))
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val FIRST_FRAGMENT_NAME = "first_name"
        fun getFragment(): FirstFragment {
            return FirstFragment()
        }
    }


}