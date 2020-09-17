package com.orly.whispers.ui.popularfeed

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orly.whispers.R
import com.orly.whispers.data.model.PopularWhispers
import com.orly.whispers.databinding.ItemWhisperBinding
import com.orly.whispers.util.DateUtil.timeElapsed

class PopularFeedAdapter :
    PagingDataAdapter<PopularWhispers, PopularFeedAdapter.PopularFeedViewHolder>(PopularWhisperDiff()) {


    override fun onBindViewHolder(holder: PopularFeedViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularFeedViewHolder =
        PopularFeedViewHolder(
            ItemWhisperBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )


    class PopularFeedViewHolder(
        private val binding: ItemWhisperBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("RestrictedApi")
        fun onBind(whispers: PopularWhispers) {
            with(binding) {
                Glide
                    .with(this.root)
                    .load(whispers.url)
                    .placeholder(R.mipmap.icon_placeholder)
                    .into(this.ivWhisper)
                this.tvMe.text = whispers.me2.toString()
                this.tvReplies.text = whispers.replies.toString()
                this.tvTime.text = timeElapsed(whispers.ts)
                if (whispers.replies > 0)
                    this.cardWhisper.setOnClickListener {
                        navigateToReplies(whispers.nickname, whispers.wid, this.root)
                    }
            }
        }

        private fun navigateToReplies(nickname: String, wid: String, view: View) {
            val direction = PopularFeedFragmentDirections
                .actionFeedToReplies(view.context.getString(R.string.response_to, nickname), wid)
            view.findNavController().navigate(direction)
        }

    }
}

private class PopularWhisperDiff : DiffUtil.ItemCallback<PopularWhispers>() {
    override fun areItemsTheSame(oldItem: PopularWhispers, newItem: PopularWhispers): Boolean {
        return oldItem.wid == newItem.wid
    }

    override fun areContentsTheSame(oldItem: PopularWhispers, newItem: PopularWhispers): Boolean {
        return oldItem == newItem
    }
}