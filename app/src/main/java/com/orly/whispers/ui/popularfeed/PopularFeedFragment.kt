package com.orly.whispers.ui.popularfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.orly.whispers.databinding.FragmentPopularFeedBinding
import com.orly.whispers.util.LoadStateFeedAdapter
import com.orly.whispers.util.ServiceLocator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

class PopularFeedFragment : Fragment() {
    private lateinit var binding: FragmentPopularFeedBinding
    private val adapter: PopularFeedAdapter = PopularFeedAdapter()
    private val viewModel: PopularFeedViewModel by viewModels {
        PopularFeedViewModelFactory(ServiceLocator.instance().getPopularFeedRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularFeedBinding.inflate(inflater)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        binding.rvPopularFeed.adapter = adapter.withLoadStateHeader(
            header = LoadStateFeedAdapter(adapter)
        )
        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { loadState ->
                binding.swipeRefresh.isRefreshing = loadState.refresh is LoadState.Loading
                binding.footerLoading.isVisible = loadState.append is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.whispers.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvPopularFeed.scrollToPosition(0) }
        }
    }
}