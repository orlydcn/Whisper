package com.orly.whispers.ui.replies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.orly.whispers.data.model.ResultResource
import com.orly.whispers.databinding.FragmentRepliesBinding
import com.orly.whispers.util.ServiceLocator

class RepliesFragment : Fragment() {
    private lateinit var binding: FragmentRepliesBinding
    private val adapter = RepliesAdapter()
    private val args: RepliesFragmentArgs by navArgs()
    private val viewModel: RepliesViewModel by viewModels {
        RepliesViewModelFactory(ServiceLocator.instance().getRepliesRepository(), args.wid)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepliesBinding.inflate(inflater)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        viewModel.getReplies()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getReplies()
        }
        binding.rvRepliesFeed.adapter = adapter
        viewModel.repliesLiveData.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                ResultResource.Status.SUCCESS -> {
                    binding.swipeRefresh.isRefreshing = false
                    adapter.submitList(result.data?.replies)
                }
                ResultResource.Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                }
                ResultResource.Status.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }
    }
}