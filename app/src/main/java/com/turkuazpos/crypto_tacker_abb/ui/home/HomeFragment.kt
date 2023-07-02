package com.turkuazpos.crypto_tacker_abb.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.turkuazpos.crypto_tacker_abb.data.local.model.PriceEntity
import com.turkuazpos.crypto_tacker_abb.databinding.FragmentHomeBinding
import com.turkuazpos.crypto_tacker_abb.databinding.ItemsCoinsBinding
import com.turkuazpos.crypto_tacker_abb.ui.base.BaseFragment
import com.turkuazpos.crypto_tacker_abb.ui.base.RecyclerListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("SetTextI18n")
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()

    override val onInflate: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    override val onBindView: FragmentHomeBinding.() -> Unit
        get() = {

            homeViewModel.getPrices()
            observeValues()
            configureListeners()
        }

    private fun observeValues() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.loading.collect {
                        binding?.root?.isRefreshing = it
                    }
                }
                launch {
                    homeViewModel.coins.collect {
                        if (binding?.recyclerView?.adapter == null) {
                            binding?.recyclerView?.adapter = adapter
                        }
                        adapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun configureListeners() {
        binding?.root?.setOnRefreshListener {
            homeViewModel.getPrices()
        }
    }

    private val adapter by lazy {
        RecyclerListAdapter<ItemsCoinsBinding, PriceEntity>(
            onInflate = ItemsCoinsBinding::inflate,
            onBind = { binding, data, _ ->
                binding.apply {
                    textViewCoinName.text = data.cryptoName
                    textViewPrice.text = "\$${data.price}"
                    root.setOnClickListener {
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                                data.cryptoName, data.id
                            )
                        )
                    }
                }
            }
        )
    }
}