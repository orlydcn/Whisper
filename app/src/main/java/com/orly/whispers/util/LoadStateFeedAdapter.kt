package com.orly.whispers.util

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.orly.whispers.ui.popularfeed.PopularFeedAdapter

class LoadStateFeedAdapter(
    private val adapter: PopularFeedAdapter
) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(parent) { adapter.retry() }
    }
}